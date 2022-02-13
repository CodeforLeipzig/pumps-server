package de.oklab.l.pumps.tree.bo

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.GenericGenerator
import org.locationtech.jts.geom.Geometry
import org.springframework.data.annotation.Immutable
import java.util.*
import javax.persistence.*

@Entity(name = "trees")
@Immutable
data class Tree(
    @Id
    var id: Long? = null,
    @Column(updatable = false, nullable = false)
    val externalId: UUID = UUID.randomUUID(),
    val standortnr: String?,
    val strasse: String?,
    val ortsteil: String?,
    @JsonProperty("baumart_wi")
    val baumartWi: String?,
    @JsonProperty("baumart_de")
    val baumartDe: String?,
    val gattung: String?,
    val pflanzjahr: Int?,
    val alter: Int?,
    @JsonProperty("dat_abgabe")
    val datAbgabe: String?,
    val xcoord: Double?,
    val ycoord: Double?,
    val timestamp: Long,
    @Column(name = "the_geom", columnDefinition = "Geometry")
    val geom: Geometry
)