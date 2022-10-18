package com.taurilogs.app.enums.ui

import com.taurilogs.app.R

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

    fun getSortIcon(): Int {
        return when (this) {
            NONE -> 0
            ASCENDING -> R.drawable.sharp_arrow_circle_up_24dp
            DESCENDING -> R.drawable.sharp_arrow_circle_down_24dp
        }
    }
}
