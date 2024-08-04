package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.dto.IncluirAreaInteresseDto
import com.LuminoSys.floodSafe.entity.AreaInteresse
import com.LuminoSys.floodSafe.repository.AreaInteresseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Point
import org.springframework.stereotype.Service

@Service
class AreaInteresseService(
    @Autowired private val areaInteresseRepository: AreaInteresseRepository
) {
    fun persistir(params: IncluirAreaInteresseDto) {
        val areaInteresseObj = AreaInteresse()
        areaInteresseObj.usuario = params.usuario
        areaInteresseObj.emiteAlertaEmail = params.emiteAlertaEmail
        areaInteresseObj.latitude = params.latitude
        areaInteresseObj.longitude = params.longitude
        areaInteresseRepository.save(areaInteresseObj)
    }
}