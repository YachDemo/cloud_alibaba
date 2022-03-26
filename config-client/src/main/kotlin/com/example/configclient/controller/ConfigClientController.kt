package com.example.configclient.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-26 14:41
 **/
@RestController
@RefreshScope
class ConfigClientController(
    @Value("\${config.info}")
    val configInfo: String
) {

    @GetMapping("/configInfo")
    fun getConfig(): String {
        return configInfo
    }
}