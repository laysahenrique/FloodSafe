package com.LuminoSys.floodSafe.repository

import com.LuminoSys.floodSafe.entity.AreaInteresse
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AreaInteresseRepository: JpaRepository<AreaInteresse, UUID> {
}