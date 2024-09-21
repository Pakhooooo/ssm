package com.ssm.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String getMD5(String input) {
        try {
            // 创建MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算MD5值
            byte[] messageDigest = md.digest(input.getBytes());
            // 转换成16进制的字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
}
