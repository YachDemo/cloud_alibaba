package com.example.feign.config

import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-26 10:58
 **/
@Configuration
open class FeignConfig {

    /**
     * feign日志打印配置
     * NONE：默认的，不显示任何日志；
     * BASIC：仅记录请求方法、URL、响应状态码及执行时间；
     * HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息；
     * FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据。
     */
    @Bean
    open fun feignLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }
}