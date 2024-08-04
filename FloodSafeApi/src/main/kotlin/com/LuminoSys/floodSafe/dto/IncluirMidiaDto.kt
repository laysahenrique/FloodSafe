package com.LuminoSys.floodSafe.dto

import java.util.*

class IncluirMidiaDto (
    var usuario: UUID,
    var longitude: Double,
    var latitude: Double,
    var arquivo: ByteArray,
    var descricao: String
)