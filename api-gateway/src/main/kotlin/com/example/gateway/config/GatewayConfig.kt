package com.example.gateway.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-26 17:11
 **/
@Configuration
class GatewayConfig(
    @Value("\${serviceUrl.userService}")
    val userService: String
) {
    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("path_route2") { r -> r.path("/user/getByUsername").uri("http://localhost:8201/user/getByUsername") }
            .build()
    }

    @Bean
    fun afterRoute(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("after_route") { r ->
                r.path("/user/getByUsername")
                    .uri(userService)
//                .predicate()
            }
            .build()
    }

}