package com.LuminoSys.floodSafe.entity

import org.hibernate.annotations.UuidGenerator
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity(name = "usuario")
class Usuario(
    @Id
    @UuidGenerator
    var id: UUID? = null,
    var email: String = "",
    var senha: String = ""
) : UserDetails {

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(
            SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_USER")
        )
    }

    override fun getPassword(): String {
        return this.senha
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
