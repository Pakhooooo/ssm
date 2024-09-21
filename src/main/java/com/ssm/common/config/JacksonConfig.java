package com.ssm.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // 自定义 JSONObject 的序列化处理
        module.addSerializer(JSONObject.class, new JsonObjectSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

}
