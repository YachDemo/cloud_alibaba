package com.example.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 启用Eureka注册中心功能
public class EurekaSecurityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSecurityServerApplication.class, args);
    }

}
