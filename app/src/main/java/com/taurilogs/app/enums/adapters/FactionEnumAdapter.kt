package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.FactionEnum

class FactionEnumAdapter {
    @ToJson
    fun toJson(enum: FactionEnum): Int {
        return enum.ordinal
    }

    @FromJson
    fun fromJson(value: Int): FactionEnum {
        return FactionEnum.values()[value]
    }
}
