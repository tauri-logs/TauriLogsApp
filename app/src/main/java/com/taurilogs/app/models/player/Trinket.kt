package com.taurilogs.app.models.player

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Trinket(
    val entry: Int,
    val guid: Int,
    val originalicon: String,
    val icon: String,
    val rarity: Int,
    val stackcount: Int,
    val ilevel: Int,
    val originalname: String,
    val name: String,
    val transmogid: Int,
    val transmogitemname: String,
    val transmogitemicon: String,
//val gems: any[],
//val ench: object,
) {
}
