package com.example.oauth2.service

import com.example.oauth2.entity.AuthUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 11:03
 **/
@Service
class UserService : UserDetailsService {
    private var userList: List<AuthUser> = ArrayList()

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @PostConstruct
    fun initData() {
        val password = passwordEncoder.encode("123456")
        userList = arrayListOf(
            AuthUser(
                "yanch",
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
            ),
            AuthUser(
                "andy",
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("client")
            ),
            AuthUser(
                "mark",
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("client")
            ),
        )
    }

    override fun loadUserByUsername(username: String): UserDetails? {
        val first = userList.firstOrNull { username == it.username }
        if (first != null) {
            return first
        } else {
            throw UsernameNotFoundException("用户名或者密码错误")
        }
    }
}