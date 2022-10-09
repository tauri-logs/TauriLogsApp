package com.taurilogs.app.models.log

class Log(
    deahts_total: Int,
    deaths_fight: Int,
    difficulty: Int,
    encounter_id: Int,
    fight_time: Int,
    guildid: Int,
    guildrid: Int,
    killtime: Int,
    log_id: Int,
    map_id: Int,
    wipes: Int,
    resurrects_fight: Int,
    member_count: Int,
    val item_count: Int,
    val pos: Int,
    val rid: Int
//    killDate?: Date,
//    difficultyName?: string
) : LogBase(
    deahts_total,
    deaths_fight,
    difficulty,
    encounter_id,
    fight_time,
    guildid,
    guildrid,
    killtime,
    log_id,
    map_id,
    wipes,
    resurrects_fight,
    member_count
) {

}

