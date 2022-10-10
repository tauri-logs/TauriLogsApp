package com.taurilogs.app.models.log

import java.time.LocalDateTime
import java.time.ZoneOffset

class Log(
    deahts_total: Int,
    deaths_fight: Int,
    difficulty: Int,
    encounter_data: Encounter,
    encounter_id: Int,
    fight_time: Int,
    guild: Guild,
    guildid: Int,
    guildrid: Int,
    killtime: Long,
    log_id: Int,
    map_id: Int,
    mapentry: Map,
    wipes: Int,
    resurrects_fight: Int,
    member_count: Int,
    val item_count: Int,
    val pos: Int,
    val rid: Int
//    difficultyName?: string
) : LogBase(
    deahts_total,
    deaths_fight,
    difficulty,
    encounter_data,
    encounter_id,
    fight_time,
    guild,
    guildid,
    guildrid,
    killtime,
    log_id,
    map_id,
    mapentry,
    wipes,
    resurrects_fight,
    member_count
) {
    val killDate: LocalDateTime
    init {
        killDate = LocalDateTime.ofEpochSecond(killtime, 0, ZoneOffset.UTC)
    }

}

