package com.taurilogs.app.models.ui

import com.taurilogs.app.enums.ui.SortEnum

class RaidDetailColumn(
    val propertyName: String,
    val displayName: String,
    val isNumeric: Boolean,
    var display: Boolean = true,
    var sort: SortEnum = SortEnum.NONE
) {
}
