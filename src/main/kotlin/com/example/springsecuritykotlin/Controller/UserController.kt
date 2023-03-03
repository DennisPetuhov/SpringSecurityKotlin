package com.example.springsecuritykotlin.Controller

import com.example.springsecuritykotlin.Model.MessageResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 4800)
@RestController
@RequestMapping("/api/test")
class UserController {
    @GetMapping("/all")
    fun allAccess(): MessageResponse {
        return MessageResponse("Public ")
    }

    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER') ")
    fun employeeAccess(): MessageResponse {
        return MessageResponse("User zone")
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    fun adminAccess(): MessageResponse {
        return MessageResponse("Admin zone")
    }


    @GetMapping("/moderator")
//    @PreAuthorize("hasRole('MODERATOR')")
    fun moderatorAccess(): MessageResponse {
        return MessageResponse("Moderator zone")
    }
}