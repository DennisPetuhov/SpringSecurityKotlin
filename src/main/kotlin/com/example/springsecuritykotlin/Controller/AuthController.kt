package com.example.springsecuritykotlin.Controller

import com.example.springsecuritykotlin.Jwt.JwtProvider.JwtTokenUtil
import com.example.springsecuritykotlin.Repository.RoleRepository
import com.example.springsecuritykotlin.Model.User
import com.example.springsecuritykotlin.Model.UserProfile
import com.example.springsecuritykotlin.Repository.UserProfileRepository
import com.example.springsecuritykotlin.Repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("*"), maxAge = 3600)
@RequestMapping("/api")
@RestController
class AuthController(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val authenticationManager: AuthenticationManager,
    private val encoder: PasswordEncoder,
    private val jwtTokenUtil: JwtTokenUtil,
    private val userProfileRepository: UserProfileRepository
) {

//    @PostMapping("/register")
//    fun register(@RequestBody user: User): ResponseEntity<String>{
////        val role: Role = roleRepository.findByName(ERole.ROLE_ADMIN)
////        user.roles.add(role)
//        val newUser = User(user.id,user.name,user.email,encoder.encode(user.password))
////        user.password = encoder.encode(user.password)
//        var user1: User = userRepository.save(newUser)
//
//
//
//        return ResponseEntity.ok().body("User Registered Successfully")
//
//
//    }





    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<String>{

        val newUser = User(user.id,user.name,user.email,encoder.encode(user.password))

        var user1: User = userRepository.save(newUser)
        val newUserProfile:UserProfile= UserProfile(user.name,user.email)
        newUserProfile.user=newUser

        userProfileRepository.save(newUserProfile)

        return ResponseEntity.ok().body("User Registered Successfully")}




    @PostMapping("/login")
    fun logIn(@RequestBody user: User): String {
        val authentication: Authentication = authenticationManager.authenticate( //
            UsernamePasswordAuthenticationToken(
                user.name,
                user.password// передача в обьект аутентификации логина и пароля
            )
        )
        SecurityContextHolder.getContext().authentication = authentication // передача обьекта аутентификации


        val jwt: String = jwtTokenUtil.generateToken(user.name ?: "bor") // генерируем токен

        val userDetails = authentication.principal as UserDetails
        println(jwt)
        return jwt
    }
}