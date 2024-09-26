package com.ssm.common.interceptor;

import com.ssm.common.service.TokenCacheService;
import com.ssm.common.util.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final RedisUtils redisUtils;

    private final TokenCacheService tokenCacheService;

    @Autowired
    public JwtTokenInterceptor(RedisUtils redisUtils, TokenCacheService tokenCacheService) {
        this.redisUtils = redisUtils;
        this.tokenCacheService = tokenCacheService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            // 尝试从缓存中获取 Token 信息
            Claims claims = tokenCacheService.getCachedToken(token);

            if (claims == null) {
                // 如果缓存未命中，解析 Token
                claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
                long remainingTime = claims.getExpiration().getTime() - System.currentTimeMillis();
                
                // 将解析结果缓存，缓存时长与 Token 剩余时长相同
                tokenCacheService.cacheToken(token, claims, remainingTime);
            }
            
            // 刷新阈值，5分钟
            long refreshThreshold = 300000;
            // 检查 Token 剩余时间
            long remainingTime = claims.getExpiration().getTime() - System.currentTimeMillis();
            if (remainingTime < refreshThreshold) {
                // 剩余时间小于阈值，刷新 Token
                String username = claims.getSubject();
                String newToken = generateNewToken(username);

                String redisKey = "auth:token:" + username;

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("authType", "Bearer");
                jsonObject.put("accessToken", newToken);
                
                // 把新的 Token 放 Redis
                redisUtils.set(redisKey, jsonObject.toString(), 1800);
                
                redisUtils.expire("user:info:" + username, 1800);
            }
        }
        return true;
    }

    // 生成新的 JWT Token
    private String generateNewToken(String username) {
        Date now = new Date();
        // 新的过期时间：30分钟
        Date expiryDate = new Date(now.getTime() + 30 * 60 * 1000); 

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
    
}
