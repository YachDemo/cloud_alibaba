package com.example.oauth2.config

import com.example.oauth2.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer

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

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.authenticationManager(authenticationManager)
            .userDetailsService(userService)
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("admin") // 配置client_id
            .secret(passwordEncoder.encode("admin123456")) // 配置client_secret
            .accessTokenValiditySeconds(3600) // 配置访问token有效期
            .refreshTokenValiditySeconds(864000) // 配置刷新token的有效期
            .redirectUris("https://www.baidu.com") // 配置redirect_uri，用于授权成功后跳转
            .scopes("all") // 配置申请的权限范围
            .authorizedGrantTypes("authorization_code","password") // 配置grant_type，表示授权类型
    }

}