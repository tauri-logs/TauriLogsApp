package com.taurilogs.app.enums

enum class DifficultyEnum(val value: Int, val description: String) {
    NM_10(3, "10NM"),
    NM_25(4, "25NM"),
    HC_10(5, "10HC"),
    HC_25(6, "25HC");

    companion object {
        fun fromValue(value: Int): DifficultyEnum {
            return values().first { it.value == value }
        }
    }
}
