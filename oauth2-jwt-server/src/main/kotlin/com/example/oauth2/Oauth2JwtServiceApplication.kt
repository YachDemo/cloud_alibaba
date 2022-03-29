package com.example.oauth2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Oauth2JwtServiceApplication

fun main(args: Array<String>) {
    runApplication<Oauth2JwtServiceApplication>(*args)
}
