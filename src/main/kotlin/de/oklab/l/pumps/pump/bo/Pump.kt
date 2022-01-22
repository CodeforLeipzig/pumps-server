package de.oklab.l.pumps.pump.bo

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import org.locationtech.jts.geom.Point

@Entity
data class Pump (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(updatable = false, nullable = false)
    val externalId: UUID = UUID.randomUUID(),
    @Column(updatable = false, nullable = false, unique = true)
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
    @Column(columnDefinition = "geography")
    val geom: Point
)