package com.example.oauth2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.runApplication

@EnableOAuth2Sso //
@SpringBootApplication
class Oauth2ClientApplication

fun main(args: Array<String>) {
    runApplication<Oauth2ClientApplication>(*args)
}
