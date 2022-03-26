package com.example.feign.controller

import com.common.Result
import com.common.entity.User
import com.example.feign.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-26 10:44
 **/
@RestController
@RequestMapping("user")
class UserFeignController {
    @Autowired
    private lateinit var userService: UserService


    @PostMapping("/create")
    fun create(@RequestBody user: User): Result<Any> {
        return userService.create(user)
    }

    @GetMapping("{id}")
    fun getUser(@PathVariable id:Long): Result<User> {
        return userService.getUser(id)
    }


    @GetMapping("/getByUsername")
    fun getByUsername(@RequestParam username: String?): Result<User> {
        return userService.getByUsername(username)
    }

    @PostMapping("/update")
    fun update(@RequestBody user: User?): Result<Any> {
        userService.update(user)
        return Result.success()
    }

    @PostMapping("/delete/{id}")
    fun delete(@PathVariable id: Long?): Result<Any> {
        userService.delete(id)
        return Result.success()
    }

}