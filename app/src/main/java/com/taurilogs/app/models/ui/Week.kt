package com.taurilogs.app.models.ui

import com.taurilogs.app.models.log.Log
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.abs

const val RESET_HOUR = 6
val RESET_DAY = DayOfWeek.WEDNESDAY

class Week(val logs: MutableList<Log>, startDate: LocalDateTime) {
    var startDate: LocalDateTime
    var endDate: LocalDateTime

    init {
        this.startDate = startDate.withHour(RESET_HOUR)
        this.endDate = startDate.withHour(RESET_HOUR).plusDays(7)
    }

    constructor(timestamp: Long) :
            this(mutableListOf(), LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC)) {
        var daysDiff = startDate.dayOfWeek.value - RESET_DAY.value
        if (daysDiff < 0) {
            daysDiff = abs(daysDiff - 1)
        }
        this.incrementStartDate(-daysDiff)
    }

    private fun incrementStartDate(numberOfDays: Int): Week {
        startDate = startDate.plusDays(numberOfDays.toLong())
        endDate = endDate.plusDays(numberOfDays.toLong())
        return this
    }
}
