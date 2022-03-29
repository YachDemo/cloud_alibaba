package com.example.oauth2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Oauth2ServiceApplication

fun main(args: Array<String>) {
    runApplication<Oauth2ServiceApplication>(*args)
}
