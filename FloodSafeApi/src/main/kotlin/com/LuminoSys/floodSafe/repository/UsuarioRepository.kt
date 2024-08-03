package com.LuminoSys.floodSafe.repository

import com.example.auth.domain.user.Usuario
import org.springframework.data.repository.Repository
import org.springframework.security.core.userdetails.UserDetails

interface UsuarioRepository: Repository<Usuario, String> {
    fun findByLogin(username: String): UserDetails
}