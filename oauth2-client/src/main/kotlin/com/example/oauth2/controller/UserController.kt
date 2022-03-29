package com.example.oauth2.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 14:49
 **/
@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("/getCurrentUser")
    fun getCurrentUser(authentication: Authentication): Any {
        return authentication
    }

}