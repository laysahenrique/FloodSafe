package com.LuminoSys.floodSafe.controller

import com.LuminoSys.floodSafe.Infra.security.TokenService
import com.LuminoSys.floodSafe.dto.AuthDto
import com.LuminoSys.floodSafe.entity.Usuario
import com.LuminoSys.floodSafe.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthenticationController(
    @Autowired private val authenticationManager: AuthenticationManager,
    @Autowired private val usuarioRepository: UsuarioRepository,
    @Autowired private val tokenService: TokenService
) {
    @PostMapping
    fun incluir(@RequestBody @Validated data: AuthDto): ResponseEntity<*> {
        if (usuarioRepository.findByEmail(data.email) != null) {
            throw BadCredentialsException("Email já cadastrado")
        }
        val usuario = Usuario()
        usuario.email = data.email
        usuario.senha = BCryptPasswordEncoder().encode(data.senha)
        usuarioRepository.save(usuario)
        return ResponseEntity.ok().build<Any>()
    }

    @PostMapping("/login")
    fun login(@RequestBody @Validated data: AuthDto): ResponseEntity<*> {
        val auth =
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(data.email, data.senha)
            ).principal
        return ResponseEntity.ok(tokenService.generateToken(auth as Usuario))
    }
}