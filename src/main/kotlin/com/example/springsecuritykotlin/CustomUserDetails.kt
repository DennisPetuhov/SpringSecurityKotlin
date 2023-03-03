package com.example.springsecuritykotlin

import com.example.springsecuritykotlin.Model.User

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class CustomUserDetails(private val user: User) : UserDetails {

//    private val log = LoggerFactory.getLogger(CustomUserDetails::class.java)

//    override fun getAuthorities(): Collection<GrantedAuthority> {
//        return user.roles.stream().map { role ->
////            log.debug("Granting Authority to user with role: $role")
//            SimpleGrantedAuthority(role.toString())
//        }.collect(Collectors.toList())
//    }

override fun getAuthorities(): Collection<GrantedAuthority> {
    return user.roles.stream().map {
//            log.debug("Granting Authority to user with role: ${it}roles")
        SimpleGrantedAuthority(it.toString())
    }.collect(Collectors.toList())
}




    override fun getPassword(): String {
        return user.password!!

    }

    override fun getUsername(): String {
        return user.name!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true


    }
}