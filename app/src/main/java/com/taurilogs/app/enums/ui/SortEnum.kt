package com.taurilogs.app.enums.ui

import com.taurilogs.app.R

enum class SortEnum(val sortIcon: Int) {
    NONE(0),
    DESCENDING(R.drawable.sharp_arrow_circle_up_24dp),
    ASCENDING(R.drawable.sharp_arrow_circle_down_24dp);

    fun next(): SortEnum {
        return when (this) {
            NONE -> DESCENDING
            DESCENDING -> ASCENDING
            ASCENDING -> NONE
        }
    }
}
