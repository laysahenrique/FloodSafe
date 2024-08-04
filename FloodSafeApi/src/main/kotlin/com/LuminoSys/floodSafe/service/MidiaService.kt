package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.dto.IncluirMidiaDto
import com.LuminoSys.floodSafe.entity.Midia
import com.LuminoSys.floodSafe.repository.MidiaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MidiaService(
    @Autowired private val midiaRepository: MidiaRepository
) {
    fun persistir(params: IncluirMidiaDto) {
        val midiaObj = Midia().apply {
            this.arquivo = params.arquivo
            this.usuario = params.usuario
            this.latitude = params.latitude
            this.longitude = params.longitude
            this.descricao = params.descricao
        }
        midiaRepository.save(midiaObj)
    }
}