package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.GenderEnum

class GenderEnumAdapter {
    @ToJson
    fun toJson(enum: GenderEnum): Int {
        return enum.ordinal
    }

    @FromJson
    fun fromJson(value: Int): GenderEnum {
        return GenderEnum.values()[value]
    }
}
