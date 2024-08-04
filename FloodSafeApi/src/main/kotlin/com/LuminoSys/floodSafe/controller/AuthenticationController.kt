package com.LuminoSys.floodSafe.controller

import com.LuminoSys.floodSafe.Infra.security.TokenService
import com.LuminoSys.floodSafe.dto.IncluirUsuario
import com.LuminoSys.floodSafe.dto.LoginUsuario
import com.LuminoSys.floodSafe.entity.Usuario
import com.LuminoSys.floodSafe.repository.UsuarioRepository
import com.LuminoSys.floodSafe.service.UsuarioService
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
    @Autowired private val usuarioService: UsuarioService,
    @Autowired private val tokenService: TokenService,
) {
    @PostMapping
    fun incluir(@RequestBody @Validated params: IncluirUsuario): ResponseEntity<*> {
        usuarioService.persistir(params)
        return ResponseEntity.ok().build<Any>()
    }

    @PostMapping("/login")
    fun login(@RequestBody @Validated data: LoginUsuario): ResponseEntity<*> {
        val auth =
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(data.email, data.senha)
            ).principal
        return ResponseEntity.ok(tokenService.generateToken(auth as Usuario))
    }
}