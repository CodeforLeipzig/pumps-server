package de.oklab.l.pumps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PumpsApplication

fun main(args: Array<String>) {
	runApplication<PumpsApplication>(*args)
}
