package com.LuminoSys.floodSafe.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.UuidGenerator
import java.time.OffsetDateTime
import java.util.*

@Entity(name = "midia")
class Midia {
    @Id
    @UuidGenerator
    var id: UUID? = null

    var usuario: UUID? = null

    var longitude: Double = 0.0

    var latitude: Double = 0.0

    var arquivo: ByteArray = ByteArray(0)

    var descricao: String = ""

    var dataInclusao: OffsetDateTime = OffsetDateTime.now()
}