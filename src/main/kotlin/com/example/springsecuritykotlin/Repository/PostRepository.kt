package com.example.springsecuritykotlin.Repository

import com.example.springsecuritykotlin.Model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PostRepository : JpaRepository<Post, Int> {
    fun findByTitle(title: String): List<Post>
    @Query(
        nativeQuery = true,
        value = "select * from posts where post.titlte=?1"
    )
    fun findByTitle1(title: String): List<Post>
    @Query(
        nativeQuery = true,
        value = "select * from posts where post.titlte=:title"
    )
    fun findByTitle2(@Param("title") title: String): List<Post>
}