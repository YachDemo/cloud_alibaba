package com.example.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 16:39
 **/
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced // 赋予负载均衡能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
