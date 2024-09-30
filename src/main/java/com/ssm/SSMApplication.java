package com.ssm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ssm.*.mapper")
@OpenAPIDefinition(
        info = @Info(
                title = "School Sports Meet Management API", // 设置 API 文档的标题
                version = "1.0", // 设置版本号
                description = "This is the API documentation for the School Sports Meet Management System.", // 描述信息
                contact = @Contact(
                        name = "Bailey Cheng", // 联系人
                        email = "baileycheng@163.com" // 联系邮箱
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080/api", description = "Local Server") // 设置服务器信息
        }
)
public class SSMApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSMApplication.class, args);
    }
}