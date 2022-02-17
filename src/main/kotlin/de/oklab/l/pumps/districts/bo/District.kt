package de.oklab.l.pumps.districts.bo

import org.locationtech.jts.geom.Geometry
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "district")
data class District (
    @Id
    var id: Long? = null,
    @Column(updatable = false, nullable = false)
    val externalId: UUID = UUID.randomUUID(),
    val ortsteil: String? = null,
    val name: String? = null,
    @Column(name = "the_geom", columnDefinition = "Geometry")
    val geom: Geometry
)