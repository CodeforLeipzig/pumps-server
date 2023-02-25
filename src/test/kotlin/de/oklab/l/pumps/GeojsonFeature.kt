package de.oklab.l.pumps

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GeojsonFeatureCollection<T>(
    val type: String = "FeatureCollection",
    val features: List<GeojsonFeature<T>>
)

data class GeojsonFeature<T>(
    val type: String = "Feature",
    val properties: T,
    val geometry: Geometry? = null,
    val id: String? = null
)

data class Geometry(
    val type: String = "Point",
    val coordinates: List<Float>? = null
)