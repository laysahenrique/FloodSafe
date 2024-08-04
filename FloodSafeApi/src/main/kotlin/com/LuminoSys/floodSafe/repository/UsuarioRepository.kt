package com.LuminoSys.floodSafe.repository

import com.example.auth.domain.user.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, UUID> {
    fun findByEmail(username: String): UserDetails?
}