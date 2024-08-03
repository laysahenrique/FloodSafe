package com.example.auth.domain.user

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.List
import java.util.UUID

@Table(name = "usuario")
@Entity(name = "usuario")
class Usuario() : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private val id: UUID? = null

    private val username: String = ""

    private val senha: String = ""

    override fun getAuthorities(): kotlin.collections.List<SimpleGrantedAuthority> {
        return listOf(
            SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_USER")
        )
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }
}