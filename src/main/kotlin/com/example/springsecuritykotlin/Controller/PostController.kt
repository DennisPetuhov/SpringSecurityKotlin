package com.example.springsecuritykotlin.Controller

import ResourceNotFoundException
import com.example.springsecuritykotlin.Model.Post
import com.example.springsecuritykotlin.Model.User
import com.example.springsecuritykotlin.Model.UserProfile
import com.example.springsecuritykotlin.Repository.PostRepository
import com.example.springsecuritykotlin.Repository.UserProfileRepository
import com.example.springsecuritykotlin.Repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postRepository: PostRepository,
    private val userProfileRepository: UserProfileRepository,
    private val userRepository: UserRepository
) {

    @PostMapping()

    fun createPost(@RequestBody post: Post): ResponseEntity<String> {
        val myUserProfile = getUserprofileFromContext()
        post.userProfile = myUserProfile
        postRepository.save(post)
        return ResponseEntity.ok().body(" User ${myUserProfile.name} have save The post ${post.title}")
    }

    @DeleteMapping("/{deleteId}")
    fun deletePost(@PathVariable deleteId: Int): ResponseEntity<*>? {
//        val myUserProfile = getUserprofileFromContext()
        return if (deleteId > 0) {
            postRepository.findById(deleteId).map<ResponseEntity<Any?>> {
                postRepository.delete(it!!)
                ResponseEntity.ok().body("Your Post  was deleted successfully")
            }.orElseThrow {
                ResourceNotFoundException(
                    "PostId $deleteId not found"
                )
            }

        } else ResponseEntity.badRequest().body("Wrong Number of Post")


    }

    @GetMapping("/{postId}")
    fun getPostById(@PathVariable postId: Int): ResponseEntity<*> {
        return if (postId > 0) {
            postRepository.findById(postId).map<ResponseEntity<Any?>> {
                ResponseEntity.ok().body(it)
            }.orElseThrow() {
                ResourceNotFoundException("Post $postId NOT Found")
            }
        } else {
            ResponseEntity.badRequest().body("POST NOT FOUND")
        }

    }

    @GetMapping("/posts/{page}/{size}/{sort}")
    fun getALLPosts(
        @PathVariable page: Int,
        @PathVariable size: Int,
        @PathVariable sort: String
    ): Page<Post> {
        val sort = Sort.by(sort)
        val page: Pageable = PageRequest.of(page, size, sort)

        return postRepository.findAll(page)
    }

    @PutMapping("/{postId}")
    fun updatePostById(@PathVariable postId: Int, @RequestBody post: Post): ResponseEntity<*> {
        return if (postId > 0) {
            postRepository.findById(postId).map<ResponseEntity<Any?>> {
                it.content = post.content
                it.title = post.title
                it.description = post.description
                var newPost = postRepository.save(it)
                ResponseEntity.ok().body(newPost)

            }.orElseThrow() {
                ResourceNotFoundException("Post $postId NOT Found")
            }
        } else {
            ResponseEntity.badRequest().body("POST NOT FOUND")
        }

    }


    private fun getUserprofileFromContext(): UserProfile {
        val authentication = SecurityContextHolder.getContext().authentication
        val userPrincipal = authentication.principal as UserDetails
        println(userPrincipal.username)

        val user: User? = userRepository.findUserByName(userPrincipal.username)

        return userProfileRepository.findUserProfileByUser(user!!)


    }
}


