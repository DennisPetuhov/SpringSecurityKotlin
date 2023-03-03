package com.example.springsecuritykotlin

import com.example.springsecuritykotlin.Repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component(value = "ud")
class UserDetailServiceImpl(var repository: UserRepository) : UserDetailsService {


    override fun loadUserByUsername(username: String): UserDetails? {
        val user = repository.findByName(username)
//        return UserSecurity(
//            user?.id, user?.email, user?.password, Collections.singleton(
//                SimpleGrantedAuthority("user")
//            )
//        )
        return user?.let {
            CustomUserDetails(it)
        }
    }

}

