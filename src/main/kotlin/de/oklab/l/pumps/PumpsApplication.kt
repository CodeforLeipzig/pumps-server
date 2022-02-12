package de.oklab.l.pumps

import com.bedatadriven.jackson.datatype.jts.JtsModule
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.PrecisionModel
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class PumpsApplication {

    @Bean
    fun jtsModule(): JtsModule = JtsModule()

    @Bean
    fun geometryFactory() = GeometryFactory(PrecisionModel(), 4326)
}

fun main(args: Array<String>) {
    runApplication<PumpsApplication>(*args)
}
