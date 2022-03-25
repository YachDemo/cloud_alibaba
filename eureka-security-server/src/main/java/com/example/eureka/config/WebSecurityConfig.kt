package com.example.eureka.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 15:16
 **/
@EnableWebSecurity
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().ignoringAntMatchers("/eureka/**")
        super.configure(http)
    }
}