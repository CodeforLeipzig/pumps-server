package de.oklab.l.pumps.pump.bo

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
data class Pump (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(updatable = false, nullable = false)
    val externalId: UUID = UUID.randomUUID(),
    val numberAnke: String? = null,
    val numberOfficial: String? = null,
    val name: String? = null,
    val district: String? = null,
    val address: String? = null,
    val lat: String? = null,
    val lon: String? = null,
    val date: String? = null,
    val description: String? = null,
    val stateDescription: String? = null,
    val feedingDescription: String? = null,
    val controlsDescription: String? = null,
    val wikipediaId: String? = null,
    val osmId: String? = null
)