package com.LuminoSys.floodSafe.dto

data class IncluirUsuario(
    val email: String,
    val senha: String,
    val nome: String
)

data class LoginUsuario(
    val email: String,
    val senha: String,
)


