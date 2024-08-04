package com.LuminoSys.floodSafe.dto

data class PrevisaoCoordenadaRetorno(
    val cidade : String,
    val descricaoTempo : String,
    val coordenadas: CoordenadasPrevisao,
    val temperaturaAtual : Double,
    val temperaturaMinima: Double,
    val temperaturaMaxima: Double,
    val velocidadeVento: Double
)

data class CoordenadasPrevisao(
    val longitude: Double,
    val latitude: Double,
)
