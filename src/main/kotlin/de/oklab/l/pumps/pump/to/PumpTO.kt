package de.oklab.l.pumps.pump.to

import com.fasterxml.jackson.annotation.JsonProperty
import de.oklab.l.pumps.pump.bo.Pump
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

data class PumpTO (

    @JsonProperty("Nummer [Anke]")
    val numberAnke: String,

    @JsonProperty("offizielle Nummer")
    val numberOfficial: String? = null,

    @JsonProperty("Bezeichnung")
    val name: String? = null,

    @JsonProperty("Stadtteil")
    val district: String? = null,

    @JsonProperty("Adresse")
    val address: String? = null,

    @JsonProperty("Koordinaten NS")
    val lat: Double,

    @JsonProperty("Koordinaten OW")
    val lon: Double,

    @JsonProperty("Datierung")
    val date: String? = null,

    @JsonProperty("Beschreibung")
    val description: String? = null,

    @JsonProperty("Status")
    val stateDescription: String? = null,

    @JsonProperty("Fütterung")
    val feedingDescription: String? = null,

    @JsonProperty("Kontrollen")
    val controlsDescription: String? = null,

    @JsonProperty("WikipediaId")
    val wikipediaId: String? = null,

    @JsonProperty("OsmId")
    val osmId: String? = null
) {
    fun toEntity(geometryFactory: GeometryFactory): Pump = Pump(
        numberAnke = numberAnke,
        numberOfficial = numberOfficial,
        name = name,
        district = district,
        address = address,
        lat = lat,
        lon = lon,
        date = date,
        description = description,
        stateDescription = stateDescription,
        feedingDescription = feedingDescription,
        controlsDescription = controlsDescription,
        wikipediaId = wikipediaId,
        osmId = osmId,
        geom = geometryFactory.createPoint(Coordinate(lon, lat))
    )

    companion object {

        fun from(pump: Pump): PumpTO = PumpTO(
                numberAnke = pump.numberAnke,
                numberOfficial = pump.numberOfficial,
                name = pump.name,
                district = pump.district,
                address = pump.address,
                lat = pump.lat,
                lon = pump.lon,
                date = pump.date,
                description = pump.description,
                stateDescription = pump.stateDescription,
                feedingDescription = pump.feedingDescription,
                controlsDescription = pump.controlsDescription,
                wikipediaId = pump.wikipediaId,
                osmId = pump.osmId
        )
    }
}