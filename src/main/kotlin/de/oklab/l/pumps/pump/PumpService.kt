package de.oklab.l.pumps.pump

import de.oklab.l.pumps.pump.bo.Pump
import de.oklab.l.pumps.pump.to.PumpTO
import org.springframework.stereotype.Service

@Service
class PumpService (val repository: PumpRepository) {

    fun create(pump: PumpTO): Pump  = repository.save(pump.toEntity())
}