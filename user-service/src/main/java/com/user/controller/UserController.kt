package com.user.controller

import com.user.entity.User
import com.common.Result
import com.user.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 15:33
 **/
@RestController
@RequestMapping("user")
open class UserController {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/create")
    fun create(@RequestBody user: User): Result<Any> {
        userService.create(user)
        return Result.success(user)
    }

    @GetMapping("{id}")
    fun getUser(@PathVariable id:Long): Result<User> {
        val user: User = userService.getUser(id)
        log.info("根据id获取用户信息，用户名称为：{}",user.userName)
        val success = Result.success(user)
        return success
    }

    @GetMapping("/getUserByIds")
    open fun getUserByIds(@RequestParam ids: List<Long?>?): Result<List<User?>?>? {
        val userList: List<User> = userService.getUserByIds(ids)
        log.info("根据ids获取用户信息，用户列表为：{}", userList)
        return Result.success(userList)
    }

    @GetMapping("/getByUsername")
    open fun getByUsername(@RequestParam username: String?): Result<User?>? {
        val user: User = userService.getByUsername(username)
        return Result.success(user)
    }

    @PostMapping("/update")
    open fun update(@RequestBody user: User?): Result<Any> {
        userService.update(user)
        return Result.success()
    }

    @PostMapping("/delete/{id}")
    open fun delete(@PathVariable id: Long?): Result<Any> {
        userService.delete(id)
        return Result.success()
    }
}