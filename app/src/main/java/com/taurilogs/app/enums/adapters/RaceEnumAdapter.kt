package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.RaceEnum

class RaceEnumAdapter {
    @ToJson
    fun toJson(enum: RaceEnum): Int {
        return enum.ordinal
    }

    @FromJson
    fun fromJson(value: Int): RaceEnum {
        return RaceEnum.values()[value]
    }
}
