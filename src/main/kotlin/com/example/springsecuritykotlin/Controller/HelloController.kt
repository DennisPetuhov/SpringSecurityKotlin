package com.example.springsecuritykotlin.Controller

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Transactional
class HelloController {
    @GetMapping
    fun hello():String{
        return "Hello"
    }
}