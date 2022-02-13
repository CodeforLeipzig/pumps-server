package de.oklab.l.pumps

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.oklab.l.pumps.pump.PumpService
import de.oklab.l.pumps.pump.to.PumpTO
import de.oklab.l.pumps.tree.TreeService
import de.oklab.l.pumps.tree.to.GeojsonFeatureCollection
import de.oklab.l.pumps.tree.to.TreeTO
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.treewalk.TreeWalk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class PumpsImporterModuleTest: BaseTest() {

    @Autowired
    lateinit var pumpService: PumpService

    @Test
    fun testImport() {
        val objectMapper = jacksonObjectMapper()
        val file = "D:\\git\\leipzig_auf_pump\\docs\\geojsons\\pumps\\alle.geojson"
        val content = FileReader(File(file)).use { it.readText() }
        val pumps: GeojsonFeatureCollection<PumpTO> = objectMapper.readValue(content, objectMapper.typeFactory.constructParametricType(
                GeojsonFeatureCollection::class.java, PumpTO::class.java
        ))
        for (pump in pumps.features.map { it.properties }) {
            pumpService.create(pump)
        }
    }
}




