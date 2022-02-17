package de.oklab.l.pumps.districts.to

import com.fasterxml.jackson.annotation.JsonProperty

data class DistrictTO (
    @JsonProperty("Name")
    val name: String? = null,
    @JsonProperty("OT")
    val ortsteil: String? = null,
    var coords: List<List<List<Float>>>? = null,
)