package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.dto.IncluirUsuario
import com.LuminoSys.floodSafe.entity.Usuario
import com.LuminoSys.floodSafe.repository.UsuarioRepository
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@ExtendWith(MockitoExtension::class)
class UsuarioServiceTest {

    @Mock
    private lateinit var usuarioRepository: UsuarioRepository

    @InjectMocks
    private lateinit var usuarioService: UsuarioService

    @Test
    fun persistir() {
        val params = IncluirUsuario(
            email = "test@example.com",
            nome = "Test User",
            senha = "password"
        )

        `when`(usuarioRepository.findByEmail(params.email)).thenReturn(Usuario())

        assertThrows(BadCredentialsException::class.java) {
            usuarioService.persistir(params)
        }
    }

    @Test
    fun persistirSemEmail() {
        val params = IncluirUsuario(
            email = "test@example.com",
            nome = "Test User",
            senha = "password"
        )

        `when`(usuarioRepository.findByEmail(params.email)).thenReturn(null)

        val bCryptPasswordEncoder = BCryptPasswordEncoder()
        val senhaCodificada = bCryptPasswordEncoder.encode(params.senha)
        val usuarioEsperado = Usuario().apply {
            email = params.email
            nome = params.nome
            senha = senhaCodificada
        }

        usuarioService.persistir(params)

        verify(usuarioRepository).save(usuarioEsperado)
    }
}
