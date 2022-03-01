package de.oklab.l.pumps.districts

import de.oklab.l.pumps.districts.bo.District
import de.oklab.l.pumps.districts.to.DistrictTO
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.stereotype.Service

@Service
class DistrictService(
    internal val repository: DistrictRepository,
    internal val geometryFactory: GeometryFactory
) {

    fun create(district: DistrictTO) {
        repository.save(
            District(
                name = district.name,
                ortsteil = district.ortsteil,
                geom = geometryFactory.createPolygon(
                    ((district.coords?.map { coords ->
                        Coordinate(
                            coords[0].toDouble(), coords[1].toDouble()
                        )
                    }) ?: emptyList()).toTypedArray()
                )
            )
        )
    }
}