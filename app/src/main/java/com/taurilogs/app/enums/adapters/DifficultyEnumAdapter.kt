package com.taurilogs.app.enums.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.taurilogs.app.enums.DifficultyEnum

class DifficultyEnumAdapter {
    @ToJson
    fun toJson(enum: DifficultyEnum): String {
        return enum.value.toString()
    }

    @FromJson
    fun fromJson(value: Int): DifficultyEnum {
        return DifficultyEnum.fromValue(value)
    }
}
