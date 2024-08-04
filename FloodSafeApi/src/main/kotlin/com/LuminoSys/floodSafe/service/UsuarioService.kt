package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.dto.IncluirAreaInteresseDto
import com.LuminoSys.floodSafe.dto.IncluirMidiaDto
import com.LuminoSys.floodSafe.dto.IncluirUsuario
import com.LuminoSys.floodSafe.entity.AreaInteresse
import com.LuminoSys.floodSafe.entity.Midia
import com.LuminoSys.floodSafe.entity.Usuario
import com.LuminoSys.floodSafe.repository.AreaInteresseRepository
import com.LuminoSys.floodSafe.repository.MidiaRepository
import com.LuminoSys.floodSafe.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Point
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    @Autowired private val usuarioRepository: UsuarioRepository
) {
    fun persistir(params: IncluirUsuario) {
        if (usuarioRepository.findByEmail(params.email) != null) {
            throw BadCredentialsException("Email já cadastrado")
        }
        val usuario = Usuario()
        usuario.email = params.email
        usuario.nome = params.nome
        usuario.senha = BCryptPasswordEncoder().encode(params.senha)
        usuarioRepository.save(usuario)
    }
}