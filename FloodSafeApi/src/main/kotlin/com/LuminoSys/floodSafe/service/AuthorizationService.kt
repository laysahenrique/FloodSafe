package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.data.repository.Repository
import org.springframework.security.core.userdetails.*
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    @Autowired private val usuarioRepository: UsuarioRepository
) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return usuarioRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")
    }
}