package com.LuminoSys.floodSafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.LuminoSys.floodSafe.entity.usuario")
class FloodSafeApplication

fun main(args: Array<String>) {
    runApplication<FloodSafeApplication>(*args)
}
