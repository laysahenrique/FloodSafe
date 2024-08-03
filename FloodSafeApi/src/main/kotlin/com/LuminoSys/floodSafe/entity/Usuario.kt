package com.example.auth.domain.user

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
class Usuario(
    @Id
    val id: UUID = UUID.randomUUID(),
    val email: String = "",
    private val senha: String = "",
) : UserDetails {

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(
            SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_USER")
        )
    }

    override fun getPassword(): String {
        return this.senha
    }

    override fun getUsername(): String {
        return this.email
    }
}