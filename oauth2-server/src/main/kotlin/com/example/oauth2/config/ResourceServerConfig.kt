package com.example.oauth2.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

/**
 * 资源服务器配置
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 11:14
 **/
@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .requestMatchers()
            .antMatchers("/user/**") //配置需要保护的资源路径
    }
}