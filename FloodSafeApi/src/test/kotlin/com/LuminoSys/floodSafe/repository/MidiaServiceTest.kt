package com.LuminoSys.floodSafe.repository

import com.LuminoSys.floodSafe.dto.IncluirMidiaDto
import com.LuminoSys.floodSafe.entity.Midia
import com.LuminoSys.floodSafe.service.MidiaService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class MidiaServiceTest {

    @Mock
    lateinit var midiaRepository: MidiaRepository

    @InjectMocks
    lateinit var midiaService: MidiaService

    @Test
    fun persistir() {
        val arquivo = byteArrayOf(1, 2, 3, 4, 5)
        val usuario = UUID.randomUUID()
        val latitude = 12.34
        val longitude = 56.78
        val descricao = "Descrição da mídia"

        val dto = IncluirMidiaDto(
            arquivo = arquivo,
            usuario = usuario,
            latitude = latitude,
            longitude = longitude,
            descricao = descricao
        )

        val midiaObj = Midia().apply {
            this.arquivo = arquivo
            this.usuario = usuario
            this.latitude = latitude
            this.longitude = longitude
            this.descricao = descricao
        }
        midiaService.persistir(dto)
        verify(midiaRepository).save(midiaObj)
    }
}