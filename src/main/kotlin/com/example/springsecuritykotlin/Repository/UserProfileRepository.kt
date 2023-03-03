package com.example.springsecuritykotlin.Repository

import com.example.springsecuritykotlin.Model.User
import com.example.springsecuritykotlin.Model.UserProfile
import org.springframework.data.jpa.repository.JpaRepository


interface UserProfileRepository: JpaRepository<UserProfile, Int> {
    fun findUserProfileByUser(user: User):UserProfile
}