package com.LuminoSys.floodSafe.Infra.security

import com.LuminoSys.floodSafe.repository.UsuarioRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    @Autowired private val tokenService: TokenService,
    @Autowired private val usuarioRepository: UsuarioRepository
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recoverToken(request)
        if (token != null) {
            usuarioRepository.findByEmail(tokenService.validateToken(token))?.let { usuario ->
                SecurityContextHolder.getContext().authentication =
                    UsernamePasswordAuthenticationToken(usuario, null, usuario.authorities)
            }
        }
        filterChain.doFilter(request, response)
    }

    fun recoverToken(request: HttpServletRequest): String? {
        var authHeader = request.getHeader("Authorization")
        if (authHeader == null) return null
        return authHeader.replace("Bearer ", "")
    }

}