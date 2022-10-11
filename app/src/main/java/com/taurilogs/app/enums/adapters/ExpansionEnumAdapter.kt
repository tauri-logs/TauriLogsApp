package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.ExpansionEnum

class ExpansionEnumAdapter {
    @ToJson
    fun toJson(enum: ExpansionEnum): Int {
        return enum.ordinal
    }

    @FromJson
    fun fromJson(value: Int): ExpansionEnum {
        return ExpansionEnum.values()[value]
    }
}
