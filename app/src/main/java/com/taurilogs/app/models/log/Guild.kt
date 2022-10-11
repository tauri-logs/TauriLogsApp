package com.taurilogs.app.models.log

import com.squareup.moshi.JsonClass
import com.taurilogs.app.enums.FactionEnum

@JsonClass(generateAdapter = true)
data class Guild(
    val faction: FactionEnum?,
    val leadername: String?,
    val name: String?,
) {
}
