package com.LuminoSys.floodSafe.Infra.security

import com.LuminoSys.floodSafe.entity.Usuario
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class TokenService(
    @Autowired @Value("\${api.security.token.secret}") private val secret: String,
) {
    fun generateToken(usuario: Usuario): String? {
        try {
            return JWT.create()
                .withIssuer("auth-api")
                .withSubject(usuario.email)
                .withExpiresAt(EXPIRED_TOKEN)
                .sign(Algorithm.HMAC256(secret))
        } catch (exception: JWTCreationException) {
            throw RuntimeException("Error creating token", exception)
        }
    }

    fun validateToken(token: String): String {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("auth-api")
                .build()
                .verify(token)
                .subject
        } catch (exception: JWTCreationException) {
           return ""
        }
    }

    companion object {
        private val EXPIRED_TOKEN = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
    }
}