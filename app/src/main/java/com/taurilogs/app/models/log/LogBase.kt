package com.taurilogs.app.models.log

import com.squareup.moshi.JsonClass

abstract class LogBase(
    val deahts_total: Int,
    val deaths_fight: Int,
    val difficulty: Int,
    val encounter_data: Encounter,
    val encounter_id: Int,
    val fight_time: Int,
    val guilddata: Guild,
    val guildid: Int,
    val guildrid: Int,
    val killtime: Long, // timestamp in s
    val log_id: Int,
    val map_id: Int,
    val mapentry: Map,
    val wipes: Int,
    val resurrects_fight: Int,
    val member_count: Int
) {

}
