package de.oklab.l.pumps.districts.bo

import org.locationtech.jts.geom.Geometry
import java.util.*
import javax.persistence.*

@Entity(name = "district")
data class District (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(updatable = false, nullable = false)
    val externalId: UUID = UUID.randomUUID(),
    val ortsteil: String? = null,
    val name: String? = null,
    @Column(name = "the_geom", columnDefinition = "Geometry")
    val geom: Geometry
)