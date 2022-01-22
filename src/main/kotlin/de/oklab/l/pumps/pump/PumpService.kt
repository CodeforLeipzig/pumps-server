package de.oklab.l.pumps.pump

import de.oklab.l.pumps.pump.bo.Pump
import de.oklab.l.pumps.pump.to.PumpTO
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.stereotype.Service

@Service
class PumpService(internal val repository: PumpRepository,
                  internal val geometryFactory: GeometryFactory) {

    fun create(pump: PumpTO): Pump = repository.save(pump.toEntity(geometryFactory))

    fun findAround(findAround: FindAround): List<PumpTO> {
        val point = geometryFactory.createPoint(Coordinate(findAround.lon, findAround.lat))
        return repository.findNearWithinDistance(point, findAround.distanceInMeters).map { PumpTO.from(it) }
    }
}