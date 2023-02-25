package de.oklab.l.pumps

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import org.apache.commons.io.FileUtils
import org.geolatte.geom.crs.CrsRegistry
import org.geolatte.geom.crs.trans.CoordinateOperation
import org.geolatte.geom.crs.trans.CoordinateOperations
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.util.UriComponentsBuilder
import org.yaml.snakeyaml.util.UriEncoder
import java.io.File
import java.net.URL

class ArcGisTest {
    val testData = mutableMapOf(
        "Bornaische Straße 3c" to Pair(12.374656, 51.309456),
        "Huygensstraße" to Pair(12.349901, 51.36657),
        "Lobstädter Straße 9" to Pair(12.38863, 51.29841),
        "Waldstraße 48" to Pair(12.35732, 51.34554),
        "Riesaer Straße 1" to Pair(12.436868, 51.34784),
        "Krystallpalast Rosa-Luxemburg Ecke Hofmeisterstrasse" to Pair(12.3874, 51.343815),
        "Wurzner Str. 143" to Pair(12.42677, 51.34355),
        "Georg-Schumann-Str. 198 (Schachtstraße)" to Pair(12.36019, 51.3638),
        "Bennigsenstr." to Pair(12.414117, 51.345608),
        "Ludwigstr. 115" to Pair(12.40026, 51.34663),
        "Mariannenstr. 91" to Pair(12.41373, 51.3465),
        "Bruhnsstraße 33" to Pair(12.429632, 51.341137),
        "Fritz-Seger-Straße" to Pair(12.37124, 51.3586),
        "Ecke Bautzmannstr. / Eisenbahnstr. 156" to Pair(12.417002, 51.344772),
        "Dorfplatz Stünz / Julius-Krause-Straße" to Pair(12.43196, 51.33844),
        "Ecke Eisenbahnstr. / Herrmann-Liebmann-Str. 79." to Pair(12.405784, 51.345387),
        "Leinestraße (bei Nr. 54)" to Pair(12.41067, 51.28604),
        "Bürgerstraße" to Pair(12.3926325, 51.28798),
        "Lindenthalerstr." to Pair(12.36543, 51.35934),
        "Peterskirche" to Pair(12.375439000000142, 51.33070470363751),
    )

    @TestFactory
    fun treesToPumps(): Collection<DynamicTest?> {
        return testData.entries.map { (key, value) ->
            DynamicTest.dynamicTest(key) {
                executeTest(key, value)
            }
        }
    }

    fun executeTest(name: String, coords: Pair<Double, Double>) {
        val baseUrl = "https://gis02.leipzig.de/arcgis2/rest/services/AGOL/Baumstarke_Stadt/MapServer/20/query"
        val xDiff = 0.5 / 73
        val yDiff = 0.5 / 111

        val x = coords.first
        val y = coords.second
        val geometry = GeometryDef(
            xmin = x - xDiff,
            ymin = y - yDiff,
            xmax = x + xDiff,
            ymax = y + yDiff,
            spatialReference = SpatialRef(wkid = 4326)
        )
        val geometry3857 = convert4326To3857(geometry)
        val objectMapper = jacksonMapperBuilder().addModule(
            KotlinModule.Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .configure(KotlinFeature.StrictNullChecks, false)
                .build()
        ).build()
        val geometryJson = objectMapper.writeValueAsString(geometry3857)
        val geometryJsonEncoded = UriEncoder.encode(geometryJson)
        val outFields = listOf(
            "objectid",
            "baumnummer",
            "gattung",
            "ga_lang_wiss",
            "ga_lang_deutsch",
            "pflanzjahr",
            "baumhoehe",
            "kr_durchm",
            "st_durchm",
            "st_umfang",
            "gebiet",
            "strasse",
            "objektkuerzel",
            "pflegebereich",
            "gefaellt_am",
            "nachpflanzung_geplant",
            "hist_standort",
            "status_patenbaum",
            "patenschaftsnummer",
            "standzeitraum",
            "anmeldung",
            "hinweis",
            "massnahme",
            "faellgrund",
            "festgelegt_am",
            "beauftragt_am",
            "ausfuehrung_bis",
            "ausgefuehrt_am",
            "storniert_am",
            "fme_tstamp",
            "letzte_bewaesserung",
            "anzeige_faellung",
            "pflanzjahr_txt",
            "ot"
        )
        val outFieldsEncode = outFields.joinToString("%2C")
        val uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
            .queryParam("f", "json")
            .queryParam("returnGeometry", true)
            .queryParam("geometry", geometryJsonEncoded)
            .queryParam("geometryType", "esriGeometryEnvelope")
            .queryParam("outFields", outFieldsEncode)
            .queryParam("outSR", "102100")
            .build()
        val file = File("C:\\Users\\Joerg\\Desktop\\${name.replace("/", "_")}.json")
        FileUtils.copyURLToFile(URL(uri.toUriString()), file)
        File(
            file.absolutePath.replace(
                ".json",
                ".geojson"
            )
        ).writeText(
            objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(objectMapper.readValue(file, ArcGisModel::class.java).to())
        )
        println("Downloaded $name, waiting 10s")
        Thread.sleep(1000 * 10)

        //println(UriEncoder.decode(geometryJsonEncoded))
        //println(UriEncoder.decode(outFieldsEncode).split(",").joinToString(", ") { "\"$it\"" })
    }
}

data class GeometryDef(
    val xmin: Double,
    val ymin: Double,
    val xmax: Double,
    val ymax: Double,
    val spatialReference: SpatialRef
)

data class SpatialRef(
    val wkid: Int
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ArcGisModel(
    val features: List<ArcGisFeature>
) {

    fun to(): GeojsonFeatureCollection<MutableMap<String, Any?>> {
        return GeojsonFeatureCollection(features = features.map { it.to() })
    }
}

data class ArcGisFeature(
    val attributes: MutableMap<String, Any?>,
    val geometry: ArcGisGeometry
) {

    fun to(): GeojsonFeature<MutableMap<String, Any?>> {
        return GeojsonFeature(id = attributes["objectid"].toString(), properties = attributes, geometry = geometry.to())
    }
}

data class ArcGisGeometry(
    val x: Double,
    val y: Double
) {
    fun to(): Geometry {
        return Geometry(coordinates = convert3857To4326(doubleArrayOf(x, y)).map { it.toFloat() })
    }
}

private fun convert4326To3857(geoDef: GeometryDef) = convert(geoDef, 3857)

private fun convert3857To4326(doubleArray: DoubleArray) = convert(doubleArray, 3857, 4326)

private fun convert(geoDef: GeometryDef, target: Int): GeometryDef {
    val sourceCRS = getCrs(geoDef.spatialReference.wkid)
    val targetCRS = getCrs(target)
    val operation = CoordinateOperations.transform(sourceCRS, targetCRS)
    val min = convert(operation, geoDef.xmin, geoDef.ymin)
    val max = convert(operation, geoDef.xmax, geoDef.ymax)
    return GeometryDef(
        xmin = min[0],
        ymin = min[1],
        xmax = max[0],
        ymax = max[1],
        spatialReference = SpatialRef(wkid = target)
    )
}

private fun convert(doubleArray: DoubleArray, source: Int, target: Int): DoubleArray {
    val sourceCRS = getCrs(source)
    val targetCRS = getCrs(target)
    val operation = CoordinateOperations.transform(sourceCRS, targetCRS)
    return convert(operation, doubleArray[0], doubleArray[1])
}


private fun getCrs(epsgCode: Int) =
    if (epsgCode == 4326) CrsRegistry.getGeographicCoordinateReferenceSystemForEPSG(epsgCode)
    else CrsRegistry.getProjectedCoordinateReferenceSystemForEPSG(epsgCode)

private fun convert(operation: CoordinateOperation, x: Double, y: Double): DoubleArray {
    val source = doubleArrayOf(x, y)
    val target = DoubleArray(2)
    operation.forward(source, target)
    return target
}