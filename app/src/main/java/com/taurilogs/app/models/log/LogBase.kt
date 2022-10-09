package com.taurilogs.app.models.log

abstract class LogBase(
    val deahts_total: Int,
    val deaths_fight: Int,
    val difficulty: Int,
    //    val encounter_data: Encounter
    val encounter_id: Int,
    val fight_time: Int,
    //    val guilddata: Guild
    val guildid: Int,
    val guildrid: Int, //* 1000 to get ms and convert to Date
    val killtime: Int,
    val log_id: Int,
    val map_id: Int,
    //    val mapentry: Map
    val wipes: Int,
    val resurrects_fight: Int,
    val member_count: Int
) {

}
