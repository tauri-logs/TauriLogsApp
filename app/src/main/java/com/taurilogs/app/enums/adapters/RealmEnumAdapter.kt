package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.RealmEnum

class RealmEnumAdapter {
    @ToJson
    fun toJson(enum: RealmEnum): String {
        return enum.realm
    }

    @FromJson
    fun fromJson(value: String): RealmEnum {
        return RealmEnum.valueOf(value)
    }
}
