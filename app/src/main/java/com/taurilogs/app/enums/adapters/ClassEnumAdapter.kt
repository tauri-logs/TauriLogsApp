package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.ClassEnum

class ClassEnumAdapter {
    @ToJson
    fun toJson(enum: ClassEnum): Int {
        return enum.ordinal
    }

    @FromJson
    fun fromJson(value: Int): ClassEnum {
        return ClassEnum.values()[value]
    }
}
