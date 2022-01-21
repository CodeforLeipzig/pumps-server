package de.oklab.l.pumps.pump

import de.oklab.l.pumps.pump.bo.Pump
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PumpRepository : JpaRepository<Pump, Long> {
}