package de.oklab.l.pumps.tree.to

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
sealed class GeojsonFeatureCollection<T, U: Geometry<V>, V>(
        val type: String = "FeatureCollection",
        val features: List<GeojsonFeature<T, U>>
)

@JsonIgnoreProperties(ignoreUnknown = true)
class PointGeojsonFeatureCollection<T>(type: String, features: List<GeojsonFeature<T, PointGeometry>>):
    GeojsonFeatureCollection<T, PointGeometry, Float>(type, features)

@JsonIgnoreProperties(ignoreUnknown = true)
class PolygonGeojsonFeatureCollection<T>(type: String, features: List<GeojsonFeature<T, PolygonGeometry>>):
    GeojsonFeatureCollection<T, PolygonGeometry, List<Float>>(type, features)

data class GeojsonFeature<T, U>(
        val type: String = "Feature",
        val properties: T,
        val geometry: Geometry<U>? = null,
        val id: String? = null
)

sealed class Geometry<T>(
    val type: String,
    val coordinates: List<T>? = null
)

class PointGeometry(
        type: String = "Point",
        coordinates: List<Float>? = null
): Geometry<Float>(type, coordinates) {
    companion object {

        fun from(lon: String?, lat: String?) = PointGeometry(
                coordinates = listOfNotNull(toCoord(lon), toCoord(lat))
        )

        fun toCoord(value: String?): Float? =
                if (value.isNullOrEmpty()) null else if (value.indexOf(".") > 0) value.toFloat() else
                    (value.substring(0, 2) + "." + value.substring(2)).toFloat()
    }
}

class PolygonGeometry(
    type: String = "Polygon",
    coordinates: List<List<Float>>? = null
): Geometry<List<Float>>(type, coordinates)