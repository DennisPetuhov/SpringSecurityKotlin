package com.example.springsecuritykotlin.Config

import com.example.springsecuritykotlin.Jwt.JwtAuthorizationFilter
import com.example.springsecuritykotlin.Jwt.JwtAuthEntryPoint
import com.example.springsecuritykotlin.Jwt.JwtProvider.JwtTokenUtil
import com.example.springsecuritykotlin.Jwt.JwtProvider.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class WebSecurityConfig(
    private val userDetailsService: UserDetailsService, private val jwtAuthEntryPoint: JwtAuthEntryPoint
) {
    private val jwtToken = JwtTokenUtil()

    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userDetailsService)
        return authenticationManagerBuilder.build()
    }


    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)
        // Put your endpoint to create/sign, otherwise spring will secure it as
        // well you won't be able to do any request
        http.authorizeHttpRequests()
            .requestMatchers("/api/**").permitAll()
            .requestMatchers("/api/login").permitAll()
//            .anyRequest().permitAll()
           .anyRequest().authenticated()
            .and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint)
            .and().authenticationManager(authenticationManager)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilter(JwtAuthenticationFilter(jwtToken, authenticationManager))
            .addFilter(JwtAuthorizationFilter(jwtToken, userDetailsService, authenticationManager))

        return http.build()
    }

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}