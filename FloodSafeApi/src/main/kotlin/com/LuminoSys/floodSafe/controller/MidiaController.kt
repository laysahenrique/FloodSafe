package com.LuminoSys.floodSafe.controller

import com.LuminoSys.floodSafe.dto.CoordenadasPrevisao
import com.LuminoSys.floodSafe.dto.IncluirAreaInteresseDto
import com.LuminoSys.floodSafe.dto.IncluirMidiaDto
import com.LuminoSys.floodSafe.dto.PrevisaoCoordenadaRetorno
import com.LuminoSys.floodSafe.repository.AreaInteresseRepository
import com.LuminoSys.floodSafe.service.AreaInteresseService
import com.LuminoSys.floodSafe.service.MidiaService
import com.LuminoSys.floodSafe.service.PrevisaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("midia")
class MidiaController(
    @Autowired private val midiaService: MidiaService,
) {

    @PostMapping
    fun incluir(@RequestBody params: IncluirMidiaDto) : ResponseEntity<*> {
        midiaService.persistir(params)
        return ResponseEntity.status(HttpStatus.CREATED).build<Any>()
    }

}