package de.oklab.l.pumps.pump

import de.oklab.l.pumps.pump.bo.Pump
import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PumpRepository : JpaRepository<Pump, Long> {

    fun findByNumberAnke(numberAnke: String): Pump?

    @Query(nativeQuery = true, value = "SELECT * FROM pump WHERE ST_DistanceSphere(geom, :point) < :distanceInMeters")
    fun findNearWithinDistance(point: Point, distanceInMeters: Double): List<Pump>
}