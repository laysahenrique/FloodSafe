package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.dto.CoordenadasPrevisao
import com.LuminoSys.floodSafe.dto.PrevisaoCoordenadaRetorno
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@ExtendWith(MockitoExtension::class)
class PrevisaoServiceTest {

    @Mock
    private lateinit var webClient: WebClient

    @Mock
    private lateinit var webClientRequest: WebClient.RequestHeadersUriSpec<*>

    @Mock
    private lateinit var webClientResponse: WebClient.ResponseSpec

    @InjectMocks
    private lateinit var previsaoService: PrevisaoService

    @Test
    fun retornaPrevisaoCoordenadas() {
        val coordenadas = CoordenadasPrevisao(latitude = 12.34, longitude = 56.78)
        val jsonResponse: JsonNode = createJsonNodeResponse()

        `when`(webClient.get()).thenReturn(webClientRequest)
        `when`(webClientRequest.uri { uriBuilder ->
            uriBuilder.queryParam("lat", coordenadas.latitude)
                .queryParam("lon", coordenadas.longitude)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .queryParam("lang", "pt_br")
                .build()
        }).thenReturn(webClientRequest)
        `when`(webClientRequest.retrieve()).thenReturn(webClientResponse)
        `when`(webClientResponse.bodyToMono(JsonNode::class.java)).thenReturn(Mono.just(jsonResponse))

        val resultado = previsaoService.retornaPrevisaoCoordenadas(coordenadas)

        val esperado = PrevisaoCoordenadaRetorno(
            cidade = "Cidade Exemplo",
            descricaoTempo = "Céu limpo",
            coordenadas = CoordenadasPrevisao(longitude = 56.78, latitude = 12.34),
            temperaturaAtual = 25.0,
            temperaturaMinima = 20.0,
            temperaturaMaxima = 30.0,
            velocidadeVento = 5.0
        )
        assertEquals(esperado, resultado)
    }

    private fun createJsonNodeResponse(): JsonNode {
        val json = """
            {
                "name": "Cidade Exemplo",
                "weather": [
                    {
                        "description": "Céu limpo"
                    }
                ],
                "coord": {
                    "lon": 56.78,
                    "lat": 12.34
                },
                "main": {
                    "feels_like": 25.0,
                    "temp_min": 20.0,
                    "temp_max": 30.0
                },
                "wind": {
                    "speed": 5.0
                }
            }
        """.trimIndent()
        return ObjectMapper().readTree(json)
    }

    companion object {
        private const val API_KEY = "8a60b2de14f7a17c7a11706b2cfcd87c"
    }
}
