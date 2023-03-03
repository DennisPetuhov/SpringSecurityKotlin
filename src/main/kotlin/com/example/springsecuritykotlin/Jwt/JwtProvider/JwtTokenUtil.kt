package com.example.springsecuritykotlin.Jwt.JwtProvider

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {

//  I’m checking if a token is valid based only on it’s expiration time
//    and you might find strange and different, but actually the validation will be done kind of automatically with
//    the method getClaims(token), if the token is wrong so a exception will be thrown

    private val secret = "YOUR_SECRET"
    private val expiration = 6000000

    fun generateToken(username: String): String =
        Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()

//    private fun getClaims(token: String) =
//        Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body

    private fun getClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
    }

    fun getName(token: String): String = getClaims(token).subject

    fun isTokenValid(token: String): Boolean {
        val claims = getClaims(token)
        val expirationDate = claims.expiration
        val now = Date(System.currentTimeMillis())
        return now.before(expirationDate)
    }
}
