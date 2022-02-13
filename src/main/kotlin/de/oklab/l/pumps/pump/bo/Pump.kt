package de.oklab.l.pumps.pump.bo

import org.hibernate.annotations.GenericGenerator
import org.locationtech.jts.geom.Geometry
import java.util.*
import javax.persistence.*

@Entity
data class Pump(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(updatable = false, nullable = false)
    val externalId: UUID = UUID.randomUUID(),
    @Column(updatable = false, nullable = false)
    val numberAnke: String,
    val numberOfficial: String? = null,
    val name: String? = null,
    val district: String? = null,
    val address: String? = null,
    val lat: Double,
    val lon: Double,
    val date: String? = null,
    val description: String? = null,
    val stateDescription: String? = null,
    val feedingDescription: String? = null,
    val controlsDescription: String? = null,
    val wikipediaId: String? = null,
    val osmId: String? = null,
    val type: String? = null,
    val physicalState: String? = null,
    val detailedPhysicalState: String? = null,
    val operatingState: String? = null,
    val lastFeeding: String? = null,
    val lastControl: String? = null,
    val wikipediaPage: String? = null,
    @Column(name = "the_geom", columnDefinition = "Geometry")
    val geom: Geometry
)