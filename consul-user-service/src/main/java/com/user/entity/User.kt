package com.user.entity

import com.common.ConstantUtils

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-25 16:03
 **/
data class User(var id: Long = ConstantUtils.ID.nextId(), var userName: String? = null, var password: String? = null)
