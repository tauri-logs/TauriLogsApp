package com.taurilogs.app.models.player

import com.squareup.moshi.Json
import com.taurilogs.app.enums.ClassEnum
import com.taurilogs.app.enums.GenderEnum
import com.taurilogs.app.enums.RaceEnum


abstract class BasePlayer(
    val name: String,
    @Json(name = "class")
    val playerClass: ClassEnum,
    val gender: GenderEnum,
    val race: RaceEnum,
) {
}
