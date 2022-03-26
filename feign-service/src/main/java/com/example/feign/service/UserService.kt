package com.example.feign.service

import org.springframework.cloud.openfeign.FeignClient
import com.common.Result
import com.common.entity.User
import org.springframework.web.bind.annotation.*

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-26 10:36
 **/
@FeignClient("user-service", fallback = UserFallbackService::class)  // 声明调用的服务
interface UserService {

    @PostMapping("user/create")
    fun create(@RequestBody user: User): Result<Any>

    /**
     * 根据id获取用户信息
     */
    @GetMapping("user/{id}")
    fun getUser(@PathVariable id: Long): Result<User>

    /**
     * 用户名称获取
     */
    @GetMapping("/user/getByUsername")
    fun getByUsername(@RequestParam username: String?): Result<User>

    /**
     * 更新
     */
    @PostMapping("/user/update")
    fun update(@RequestBody user: User?): Result<Any>

    /**
     * 删除
     */
    @PostMapping("/user/delete/{id}")
    fun delete(@PathVariable id: Long?): Result<Any>
}