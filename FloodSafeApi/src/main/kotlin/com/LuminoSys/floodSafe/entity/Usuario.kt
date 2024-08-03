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

    @Column(name = "username",nullable = false, unique = true)
    val username: String = ""

    @Column(name = "senha", nullable = false)
    val senha: String = ""
}