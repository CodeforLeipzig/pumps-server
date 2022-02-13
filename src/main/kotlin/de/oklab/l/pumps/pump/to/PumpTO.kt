package de.oklab.l.pumps.pump.to

import de.oklab.l.pumps.pump.bo.Pump
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

data class PumpTO(
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
        type = type,
        physicalState = physicalState,
        detailedPhysicalState = detailedPhysicalState,
        operatingState = operatingState,
        lastFeeding = lastFeeding,
        lastControl = lastControl,
        wikipediaPage = wikipediaPage,
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
            osmId = pump.osmId,
            type = pump.type,
            physicalState = pump.physicalState,
            detailedPhysicalState = pump.detailedPhysicalState,
            operatingState = pump.operatingState,
            lastFeeding = pump.lastFeeding,
            lastControl = pump.lastControl,
            wikipediaPage = pump.wikipediaPage
        )
    }
}