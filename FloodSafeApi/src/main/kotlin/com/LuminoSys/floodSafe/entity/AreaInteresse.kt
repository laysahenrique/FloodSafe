package com.LuminoSys.floodSafe.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.UuidGenerator
import org.springframework.data.geo.Point
import java.util.*

@Entity(name = "area_interesse")
class AreaInteresse() {
    @Id
    @UuidGenerator
    var id: UUID? = null

    var usuario: UUID? = null

    var longitude: Double = 0.0

    var latitude: Double = 0.0

    var emiteAlertaEmail : Boolean = false
}