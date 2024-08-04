package com.LuminoSys.floodSafe.controller

import com.LuminoSys.floodSafe.dto.CoordenadasPrevisao
import com.LuminoSys.floodSafe.dto.IncluirAreaInteresseDto
import com.LuminoSys.floodSafe.dto.PrevisaoCoordenadaRetorno
import com.LuminoSys.floodSafe.repository.AreaInteresseRepository
import com.LuminoSys.floodSafe.service.AreaInteresseService
import com.LuminoSys.floodSafe.service.PrevisaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("area-interesse")
class AreaInteresseController(
    @Autowired private val areaInteresseService: AreaInteresseService,
    @Autowired private val previsaoService: PrevisaoService,
    private val areaInteresseRepository: AreaInteresseRepository
) {

    @PostMapping
    fun incluir(@RequestBody params: IncluirAreaInteresseDto) : ResponseEntity<*> {
        areaInteresseService.persistir(params)
        return ResponseEntity.status(HttpStatus.CREATED).build<Any>()
    }

    @GetMapping("{id}/previsao")
    fun previsaoArea(@PathVariable id: UUID): PrevisaoCoordenadaRetorno {
        val areaInteresse = areaInteresseRepository.findById(id).get()
        return previsaoService.retornaPrevisaoCoordenadas(
            CoordenadasPrevisao(
                areaInteresse.longitude,
                areaInteresse.latitude
            )
        )
    }

}