package com.example.oauth2.config

import com.example.oauth2.common.JwtTokenEnhancer
import com.example.oauth2.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

/**
 * 认证服务器配置
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 11:06
 **/
@Configuration
@EnableAuthorizationServer // 开启认证服务器
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userService: UserService

    /*
    redis 存储配置
     */
//    @Autowired
//    @Qualifier("redisTokenStore")
//    private lateinit var redisTokenStore: TokenStore

    /*
    jwt 存储配置
     */
    @Autowired
    @Qualifier("jwtTokenStore")
    private lateinit var jwtTokenStore: TokenStore

    @Autowired
    private lateinit var jwtAccessTokenConverter: JwtAccessTokenConverter

    @Autowired
    private lateinit var jwtTokenEnhancer: JwtTokenEnhancer

    /**
     * 使用密码模式需要配置
     */
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        val tokenEnhancerChain = TokenEnhancerChain()
        val delegates = ArrayList<TokenEnhancer>()
        delegates.add(jwtTokenEnhancer)  // 配置jwt内容增强器
        delegates.add(jwtAccessTokenConverter)

        tokenEnhancerChain.setTokenEnhancers(delegates)

        endpoints.authenticationManager(authenticationManager)
            .userDetailsService(userService)
            .tokenStore(jwtTokenStore) // 配置令牌桶存储策略
            .accessTokenConverter(jwtAccessTokenConverter)
            .tokenEnhancer(tokenEnhancerChain)
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("admin") // 配置client_id
            .secret(passwordEncoder.encode("admin123456")) // 配置client_secret
            .accessTokenValiditySeconds(3600) // 配置访问token有效期
            .refreshTokenValiditySeconds(864000) // 配置刷新token的有效期
//            .redirectUris("https://www.baidu.com") // 配置redirect_uri，用于授权成功后跳转
            .redirectUris("http://localhost:9501/login") //单点登录时配置
            .autoApprove(true)
            .scopes("all") // 配置申请的权限范围
            .authorizedGrantTypes("authorization_code", "password", "refresh_token") // 配置grant_type，表示授权类型
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("isAuthenticated()") // 获取密钥需要身份认证，使用单点登录时必须配置
    }

}