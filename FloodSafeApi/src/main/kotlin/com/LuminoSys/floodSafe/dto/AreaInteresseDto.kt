package com.LuminoSys.floodSafe.dto

import java.util.UUID

data class IncluirAreaInteresseDto(
    val latitude: Double,
    val longitude: Double,
    val usuario: UUID,
    val emiteAlertaEmail: Boolean ,
)