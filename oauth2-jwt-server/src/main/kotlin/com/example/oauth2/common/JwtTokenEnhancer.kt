package com.example.oauth2.common

import com.example.oauth2.entity.AuthUser
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer


/**
 * jwt内容增强
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 15:25
 **/
class JwtTokenEnhancer : TokenEnhancer {
    override fun enhance(accessToken: OAuth2AccessToken, oa: OAuth2Authentication): OAuth2AccessToken {
        val info = HashMap<String, Any>()
        info["enhance"] = "enhance info"
        (accessToken as DefaultOAuth2AccessToken).additionalInformation = info
        return accessToken
    }
}