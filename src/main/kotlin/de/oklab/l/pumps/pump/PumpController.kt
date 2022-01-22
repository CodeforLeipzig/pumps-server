package de.oklab.l.pumps.pump

import de.oklab.l.pumps.pump.to.PumpTO
import org.springframework.http.HttpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController("/pumps")
class PumpController (private val service: PumpService) {

    @PostMapping
    fun create(pump: PumpTO, httpRequest: HttpRequest): ResponseEntity<Any> {
        val pumpBo = service.create(pump)
        val uri = UriComponentsBuilder.fromUri(httpRequest.uri).path(pumpBo.externalId.toString()).build().toUri()
        return ResponseEntity.created(uri).build()
    }

    @PostMapping("nearBy")
    fun findAround(@RequestBody findAround: FindAround): List<PumpTO> {
        return service.findAround(findAround)
    }
}

data class FindAround(
        val lat: Double,
        val lon: Double,
        val distanceInMeters: Double
)