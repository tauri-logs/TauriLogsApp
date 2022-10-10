package com.taurilogs.app.models.log


class RaidDetail(
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
    val rid: Int,
//    val members: List<Member>
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

}
