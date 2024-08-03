package com.LuminoSys.floodSafe.service

import com.LuminoSys.floodSafe.Dto.CoordenadasPrevisao
import com.LuminoSys.floodSafe.Dto.PrevisaoCoordenadaRetorno
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class PrevisaoService {

    fun retornaPrevisaoCoordenadas(coordenadas : CoordenadasPrevisao): PrevisaoCoordenadaRetorno {
        val retorno = requisitarPrevisaoCoordenadas(coordenadas)
        return PrevisaoCoordenadaRetorno(
            cidade = retorno["name"].asText(),
            descricaoTempo = retorno.at("/weather/0/description").asText(),
            coordenadas = CoordenadasPrevisao(
                longitude = retorno.at("/coord/lon").asDouble(),
                latitude= retorno.at("/coord/lat").asDouble(),
            ),
            temperaturaAtual = retorno.at("/main/feels_like").asDouble(),
            temperaturaMinima = retorno.at("/main/temp_min").asDouble(),
            temperaturaMaxima = retorno.at("/main/temp_max").asDouble(),
            velocidadeVento = retorno.at("/wind/speed").asDouble()
        )
    }

    fun requisitarPrevisaoCoordenadas(coordenadas : CoordenadasPrevisao) : JsonNode {

        return WebClient.create(URL_OPEN_WEATHER)
            .get()
            .uri { uriBuilder ->
                 uriBuilder
                    .queryParam("lat", coordenadas.latitude)
                    .queryParam("lon", coordenadas.longitude)
                    .queryParam("appid", API_KEY)
                    .queryParam("units", "metric")
                    .queryParam("lang", "pt_br")
                    .build()
            }
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .block() ?: throw InternalError("Não foi possivel requisitar a previsão do tempo para o local.")
    }

    companion object {
        private const val URL_OPEN_WEATHER = "https://api.openweathermap.org/data/2.5/weather"
        private const val API_KEY = "8a60b2de14f7a17c7a11706b2cfcd87c"
    }

}