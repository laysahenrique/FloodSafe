package com.LuminoSys.floodSafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class FloodSafeApplication

fun main(args: Array<String>) {
	runApplication<FloodSafeApplication>(*args)
}
