package com.example.oauth2.config

import com.example.oauth2.common.JwtTokenEnhancer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

/**
 * 使用Jwt存储token的配置
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 15:15
 **/
@Configuration
class JwtTokenStoreConfig(val signKey: String = "test_key") {

    @Bean
    fun jwtTokenStore(jwtAccessTokenConverter: JwtAccessTokenConverter): TokenStore {
        return JwtTokenStore(jwtAccessTokenConverter)
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val jwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setSigningKey(signKey)
        return jwtAccessTokenConverter
    }

    @Bean
    fun jwtTokenEnhancer(): JwtTokenEnhancer {
        return JwtTokenEnhancer()
    }
}