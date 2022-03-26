package com.example.feign.service

import com.common.Result
import com.common.entity.User
import org.springframework.stereotype.Component

/**
 * 服务降级
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-26 10:50
 **/
@Component
class UserFallbackService : UserService {
    override fun create(user: User): Result<Any> {
        val user = User(-1L, "3123212", "31231")
        return Result.success(user)
    }

    override fun getUser(id: Long): Result<User> {
        val user = User(-2L, "3123212", "31231");
        return Result.success(user)
    }

    override fun getByUsername(username: String?): Result<User> {
        val user = User(-3L, "3123212", "31231");
        return Result.success(user)
    }

    override fun update(user: User?): Result<Any> {
        return Result.error("服务降级")
    }

    override fun delete(id: Long?): Result<Any> {
        return Result.error("服务降级")
    }

}
