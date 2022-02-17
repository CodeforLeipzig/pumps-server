package de.oklab.l.pumps

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.oklab.l.pumps.districts.DistrictService
import de.oklab.l.pumps.districts.to.DistrictTO
import de.oklab.l.pumps.tree.to.PolygonGeojsonFeatureCollection
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.io.FileReader

class DistrictsImporterModuleTest : BaseTest() {

    @Autowired
    lateinit var districtService: DistrictService

    @Test
    fun testImport() {
        val objectMapper = jacksonObjectMapper()
        val file = "D:\\git\\leipzig_auf_pump\\docs\\geojsons\\districts\\alle.geojson"
        val content = FileReader(File(file)).use { it.readText() }
        val districts: PolygonGeojsonFeatureCollection<DistrictTO> =
            objectMapper.readValue(
                content, objectMapper.typeFactory.constructParametricType(
                    PolygonGeojsonFeatureCollection::class.java, DistrictTO::class.java
                )
            )
        for (district in districts.features.map { feature ->
            feature.properties.also { prop ->
                prop.coords = feature.geometry?.coordinates?.map { it.coordinates ?: emptyList() } ?: emptyList()
            }
        }) {
            districtService.create(district)
        }
    }
}




