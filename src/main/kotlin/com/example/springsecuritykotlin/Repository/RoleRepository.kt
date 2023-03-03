package com.example.springsecuritykotlin.Repository

import com.example.springsecuritykotlin.Model.ERole
import com.example.springsecuritykotlin.Model.Role
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository:JpaRepository<Role,Id> {
    fun findByName(role: ERole): Role
}