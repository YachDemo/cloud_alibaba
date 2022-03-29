package com.user.service

import com.user.entity.User

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 15:37
 **/
interface UserService {
    /**
     * 创建用户
     */
    fun create(user: User)

    /**
     * 根据id获取用户信息
     */
    fun getUser(id: Long): User

    /**
     * 根据id获取
     */
    fun getUserByIds(ids: List<Long?>?): List<User>

    /**
     * 用户名称获取
     */
    fun getByUsername(username: String?): User

    /**
     * 更新
     */
    fun update(user: User?)

    /**
     * 删除
     */
    fun delete(id: Long?)
}