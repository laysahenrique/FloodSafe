package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.dto.IncluirAreaInteresseDto
import com.LuminoSys.floodSafe.entity.AreaInteresse
import com.LuminoSys.floodSafe.repository.AreaInteresseRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class AreaInteresseServiceTest {

    @Mock
    lateinit var areaInteresseRepository: AreaInteresseRepository

    @InjectMocks
    lateinit var areaInteresseService: AreaInteresseService

    @Captor
    lateinit var areaInteresseCaptor: ArgumentCaptor<AreaInteresse>

    @Test
    fun persistir() {
        val dto = IncluirAreaInteresseDto(
            usuario = UUID.randomUUID(),
            emiteAlertaEmail = true,
            latitude = 12.34,
            longitude = 56.78
        )

        areaInteresseService.persistir(dto)

        verify(areaInteresseRepository).save(areaInteresseCaptor.capture())
        val savedAreaInteresse = areaInteresseCaptor.value

        assertEquals(dto.usuario, savedAreaInteresse.usuario)
        assertEquals(dto.emiteAlertaEmail, savedAreaInteresse.emiteAlertaEmail)
        assertEquals(dto.latitude, savedAreaInteresse.latitude)
        assertEquals(dto.longitude, savedAreaInteresse.longitude)
    }
}
