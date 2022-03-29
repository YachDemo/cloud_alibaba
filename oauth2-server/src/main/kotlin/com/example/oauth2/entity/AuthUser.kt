package com.example.oauth2.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 *
 * @author YanCh
 * @version v1.0
 * Create by 2022-03-29 14:26
 **/
data class AuthUser(
    private val username: String,
    private val password: String,
    private val authorities: List<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): List<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}