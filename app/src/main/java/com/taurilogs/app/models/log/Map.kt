package com.taurilogs.app.models.log

import com.taurilogs.app.enums.ExpansionEnum

data class Map(
    val expansion: ExpansionEnum,
    val id: Long,
    val name: String,
    val type: Int
) {
}
