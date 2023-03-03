package com.example.springsecuritykotlin.Jwt

import com.fasterxml.jackson.databind.ObjectMapper

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException


@Component
class JwtAuthEntryPoint : AuthenticationEntryPoint {

//   override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
////        logger.error("Unauthorized error: {}", authException.message)
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE)
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
//        val body: MutableMap<String, Any> = HashMap()
//        body.put("status", HttpServletResponse.SC_UNAUTHORIZED)
//        body.put("error", "Unauthorized")
//        authException.message?.let { body.put("message", it) }
//        body.put("path", request.getServletPath())
//        val mapper = ObjectMapper()
//        mapper.writeValue(response.getOutputStream(), body)
//    }

//    companion object {
////        private val logger: Logger = LoggerFactory.getLogger(AuthEntryPointJwt::class.java)
//    }
@Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: org.springframework.security.core.AuthenticationException?
    ) {
        response!!.setContentType(MediaType.APPLICATION_JSON_VALUE)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
        val body: MutableMap<String, Any> = HashMap()
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED)
        body.put("error", "Unauthorized")
    body.put("message", authException!!.message!!)
        body.put("path", request!!.getServletPath())
        val mapper = ObjectMapper()
        mapper.writeValue(response.getOutputStream(), body)
    }


}