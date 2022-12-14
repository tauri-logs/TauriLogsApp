package com.taurilogs.app.models.log

import com.squareup.moshi.JsonClass
import com.taurilogs.app.enums.DifficultyEnum
import java.time.LocalDateTime
import java.time.ZoneOffset

@JsonClass(generateAdapter = true)
class Log(
    deahts_total: Int,
    deaths_fight: Int,
    difficulty: DifficultyEnum,
    encounter_data: Encounter,
    encounter_id: Int,
    fight_time: Int,
    guilddata: Guild,
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
    val rid: Int,
) : LogBase(
    deahts_total,
    deaths_fight,
    difficulty,
    encounter_data,
    encounter_id,
    fight_time,
    guilddata,
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
    val difficultyName: String
    init {
        killDate = LocalDateTime.ofEpochSecond(killtime, 0, ZoneOffset.UTC)
        difficultyName = "${encounter_data.encounter_name} ${difficulty.description}"
    }

}

