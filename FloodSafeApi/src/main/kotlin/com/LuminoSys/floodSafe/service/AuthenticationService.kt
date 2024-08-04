package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.repository.UsuarioRepository
import org.springframework.context.annotation.Lazy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(@Lazy private val usuarioRepository: UsuarioRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        return usuarioRepository.findByEmail(email) ?: throw UsernameNotFoundException("Usuário não encontrado")
    }
}
