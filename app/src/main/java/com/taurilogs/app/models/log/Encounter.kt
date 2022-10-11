package com.taurilogs.app.models.log

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Encounter(
    val encounter_difficulty: Int,
    val encounter_id: Int,
    val encounter_index: Int,
    val encounter_map: Int,
    val encounter_name: String,
    val encounter_sorting: Int
) {

}
