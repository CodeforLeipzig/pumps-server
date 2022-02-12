package de.oklab.l.pumps.tree.bo

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.annotations.GenericGenerator
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.Point
import java.util.*
import javax.persistence.*

@Entity(name = "tree_history")
data class TreeHistory (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
        @GeneratedValue(generator = "UUIDGenerator")
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