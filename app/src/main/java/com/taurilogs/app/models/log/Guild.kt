package com.taurilogs.app.models.log

import com.taurilogs.app.enums.FactionEnum

data class Guild(
    val faction: FactionEnum?,
    val leadername: String?,
    val name: String?,
) {
}
