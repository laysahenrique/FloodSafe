package com.LuminoSys.floodSafe.repository

import com.LuminoSys.floodSafe.entity.Midia
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MidiaRepository: JpaRepository<Midia, UUID> {
}