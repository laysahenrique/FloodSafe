package com.LuminoSys.floodSafe.controller

import com.LuminoSys.floodSafe.dto.CoordenadasPrevisao
import com.LuminoSys.floodSafe.dto.PrevisaoCoordenadaRetorno
import com.LuminoSys.floodSafe.service.PrevisaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("previsao")
class PrevisaoController(
    @Autowired private val previsaoService: PrevisaoService,
) {
    @GetMapping
    fun previsaoPorCoordenadas(
        @RequestParam longitude: Double,
        @RequestParam latitude: Double
    ): PrevisaoCoordenadaRetorno {
        return previsaoService.retornaPrevisaoCoordenadas(CoordenadasPrevisao(longitude, latitude))
    }
}