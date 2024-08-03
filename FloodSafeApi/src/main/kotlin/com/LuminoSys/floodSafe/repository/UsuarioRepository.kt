package com.LuminoSys.floodSafe.repository

import com.example.auth.domain.user.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@EnableJpaRepositories
interface UsuarioRepository : JpaRepository<Usuario, UUID> {
    fun findByEmail(username: String): Usuario?
}