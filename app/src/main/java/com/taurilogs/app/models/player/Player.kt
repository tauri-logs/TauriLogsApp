package com.taurilogs.app.models.player

import com.squareup.moshi.JsonClass
import com.taurilogs.app.enums.ClassEnum
import com.taurilogs.app.enums.GenderEnum
import com.taurilogs.app.enums.RaceEnum

@JsonClass(generateAdapter = true)
class Player(
    name: String,
    playerClass: ClassEnum,
    gender: GenderEnum,
    race: RaceEnum,
) : BasePlayer(
    name,
    playerClass,
    gender,
    race
) {

}
