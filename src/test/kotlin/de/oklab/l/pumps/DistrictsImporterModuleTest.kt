package de.oklab.l.pumps

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.oklab.l.pumps.districts.DistrictService
import de.oklab.l.pumps.districts.to.DistrictTO
import de.oklab.l.pumps.tree.to.Geometry
import de.oklab.l.pumps.tree.to.GeometryDeserializer
import de.oklab.l.pumps.tree.to.PolygonGeojsonFeatureCollection
import de.oklab.l.pumps.tree.to.PolygonGeometry
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

class DistrictsImporterModuleTest : BaseTest() {

    @Autowired
    lateinit var districtService: DistrictService

    @Test
    fun testImport() {
        val objectMapper = jacksonObjectMapper()
        val module = SimpleModule()
        module.addDeserializer(Geometry::class.java, GeometryDeserializer())
        objectMapper.registerModule(module)
        val directory = File("D:\\git\\leipzig_auf_pump\\docs\\geojsons\\districts")
        for (file in directory.listFiles().filter { !it.name.contentEquals("alle.geojson") }) {
            val content = FileReader(file, Charset.forName("UTF-8")).use { it.readText() }
            val districts: PolygonGeojsonFeatureCollection<DistrictTO> =
                objectMapper.readValue(
                    content, objectMapper.typeFactory.constructParametricType(
                        PolygonGeojsonFeatureCollection::class.java, DistrictTO::class.java
                    )
                )
            for (district in districts.features.map { feature ->
                feature.properties.also { prop ->
                    prop.coords = (feature.geometry?.coordinates ?: emptyList()) as List<List<Float>>?
                }
            }) {
                districtService.create(district)
            }
        }
    }
}




