package com.LuminoSys.floodSafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.LuminoSys.floodSafe.repository"])
@EntityScan(basePackages = ["com.LuminoSys.floodSafe.entity"])
class FloodSafeApplication

fun main(args: Array<String>) {
    runApplication<FloodSafeApplication>(*args)
}
