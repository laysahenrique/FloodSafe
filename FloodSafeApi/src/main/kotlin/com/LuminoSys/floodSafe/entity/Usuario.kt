package com.LuminoSys.floodSafe.entity

import jakarta.persistence.*
import java.util.UUID

@Entity(name = "usuario")
@Table(name = "usuario")
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: UUID? = null

    @Column(name = "login",nullable = false, unique = true)
    val login: String = ""

    @Column(name = "senha", nullable = false)
    val senha: String = ""
}