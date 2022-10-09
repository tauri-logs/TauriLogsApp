package com.taurilogs.app.api.responses

import com.taurilogs.app.models.log.Log

data class PlayerRaidResponse (
    val logs: List<Log>
)
