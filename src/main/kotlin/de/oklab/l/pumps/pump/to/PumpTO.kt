package de.oklab.l.pumps.pump.to

import com.fasterxml.jackson.annotation.JsonProperty
import de.oklab.l.pumps.pump.bo.Pump

data class PumpTO (

    @JsonProperty("Nummer [Anke]")
    val numberAnke: String? = null,

    @JsonProperty("offizielle Nummer")
    val numberOfficial: String? = null,

    @JsonProperty("Bezeichnung")
    val name: String? = null,

    @JsonProperty("Stadtteil")
    val district: String? = null,

    @JsonProperty("Adresse")
    val address: String? = null,

    @JsonProperty("Koordinaten NS")
    val lat: String? = null,

    @JsonProperty("Koordinaten OW")
    val lon: String? = null,

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
    fun toEntity(): Pump = Pump(
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
        osmId = osmId
    )
}