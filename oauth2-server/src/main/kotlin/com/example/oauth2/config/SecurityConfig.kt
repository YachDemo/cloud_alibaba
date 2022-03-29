package com.example.oauth2.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 11:32
 **/
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManager()
    }

    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/oauth/**", "/login/**", "/logout/**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .permitAll()
    }

}