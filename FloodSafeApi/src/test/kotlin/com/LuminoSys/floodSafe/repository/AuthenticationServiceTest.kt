package com.LuminoSys.floodSafe.repository

import com.LuminoSys.floodSafe.service.AuthenticationService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

@ExtendWith(MockitoExtension::class)
class AuthenticationServiceTest {
    @Mock
    lateinit var usuarioRepository: UsuarioRepository

    @InjectMocks
    lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setup() {
        val email = "test@example.com"
        val userDetails: UserDetails = User.withUsername(email).password("password").roles("USER").build()
        Mockito.doReturn(userDetails).`when`(usuarioRepository).findByEmail(email)

        val nonExistentEmail = "nonexistent@example.com"
        Mockito.doReturn(null).`when`(usuarioRepository).findByEmail(nonExistentEmail)
    }

    @Test
    fun loadUserByUsername() {
        val email = "test@example.com"
        val result = authenticationService.loadUserByUsername(email)
        val expectedUserDetails: UserDetails = User.withUsername(email).password("password").roles("USER").build()
        assertEquals(expectedUserDetails, result)
    }

    @Test
    fun loadUserByUsernameError() {
        val email = "nonexistent@example.com"
        assertThrows(UsernameNotFoundException::class.java) {
            authenticationService.loadUserByUsername(email)
        }
    }
}