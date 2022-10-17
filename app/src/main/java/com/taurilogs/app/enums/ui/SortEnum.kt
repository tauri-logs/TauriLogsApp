package com.taurilogs.app.enums.ui

enum class SortEnum {
    NONE,
    DESCENDING,
    ASCENDING;

    fun next(): SortEnum {
        return when (this) {
            NONE -> DESCENDING
            DESCENDING -> ASCENDING
            ASCENDING -> NONE
        }
    }
}
