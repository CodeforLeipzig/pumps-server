package de.oklab.l.pumps.pump

import de.oklab.l.pumps.pump.to.PumpTO
import org.springframework.http.HttpRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.util.UriComponentsBuilder

@Controller("/pumps")
class PumpController (private val service: PumpService) {

    @PostMapping
    fun create(pump: PumpTO, httpRequest: HttpRequest): ResponseEntity<Any> {
        val pumpBo = service.create(pump)
        val uri = UriComponentsBuilder.fromUri(httpRequest.uri).path(pumpBo.externalId.toString()).build().toUri()
        return ResponseEntity.created(uri).build()
    }
}