package com.taurilogs.app.models.player

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.taurilogs.app.enums.ClassEnum
import com.taurilogs.app.enums.GenderEnum
import com.taurilogs.app.enums.RaceEnum
import com.taurilogs.app.enums.SpecEnum

@JsonClass(generateAdapter = true)
class Member(
    name: String,
    @Json(name = "class")
    playerClass: ClassEnum,
    gender: GenderEnum,
    race: RaceEnum,
    val valid_player: Boolean,
    val guid: Long,
    val link: String, //  r=[EN] Evermoon&amp;n=Ivan&amp;gn=Pretty Solid Guild
    val spec: SpecEnum,
    val dmg_done: Long,
    val dmg_taken: Long,
    val dmg_absorb: Long,
    val heal_done: Long,
    val absorb_done: Long,
    val overheal: Long,
    val heal_taken: Long,
    val interrupts: Int,
    val dispells: Int,
    val ilvl: Int,
    val talents: String?,
    val trinket_0: Trinket?,
    val trinket_1: Trinket?,
    var dps: Long?,
    var hps: Long?
): BasePlayer(
    name,
    playerClass,
    gender,
    race
) {

}
