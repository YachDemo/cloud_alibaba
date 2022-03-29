package com.example.oauth2.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

/**
 * redis方式存储令牌桶
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 15:08
 **/
//@Configuration
class RedisTokenStoreConfig {

    @Autowired
    private lateinit var redisConnectionFactory: RedisConnectionFactory

    @Bean
    fun redisTokenStore(): TokenStore {
        return RedisTokenStore(redisConnectionFactory)
    }

}