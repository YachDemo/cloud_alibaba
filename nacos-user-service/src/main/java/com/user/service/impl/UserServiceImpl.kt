package com.user.service.impl

import com.common.ConstantUtils
import com.user.entity.User
import com.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun create(user: User) {
        TODO("Not yet implemented")
    }

    override fun getUser(id: Long): User {
        val user = User()
        user.password = "12321"
        user.userName = "31231"
        user.id = ConstantUtils.ID.nextId()
        return user
    }

    override fun getUserByIds(ids: List<Long?>?): List<User> {
        val userArr = ArrayList<User>()
        val user = User()
        user.userName = "123"
        user.password = "12312"
        userArr.add(user)
        return userArr
    }

    override fun getByUsername(username: String?): User {
        val user = User()
        return user
    }

    override fun update(user: User?) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long?) {
        TODO("Not yet implemented")
    }
}