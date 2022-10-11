package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.enums.SpecEnum

class SpecEnumAdapter {
    @ToJson
    fun toJson(enum: SpecEnum): Int {
        return enum.value
    }

    @FromJson
    fun fromJson(value: Int): SpecEnum {
        return SpecEnum.values().find { it.value == value }!!
    }
}
