package com.example.springsecuritykotlin.Repository

import com.example.springsecuritykotlin.Model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {

//    fun findByUserAndName(name: String?): User?
    fun findUserByName(name: String?): User?
    fun findByName(name: String?): User?
    fun existsByName(userName: String?): Boolean?
    fun existsByEmail(email: String?): Boolean?
}