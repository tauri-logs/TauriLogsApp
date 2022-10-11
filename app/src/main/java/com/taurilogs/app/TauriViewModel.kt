package com.taurilogs.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.models.Week
import com.taurilogs.app.models.log.Log
import kotlinx.coroutines.launch
import java.time.ZoneOffset

class TauriViewModel(private val service: TauriWebService) : ViewModel() {

    private val logs = MutableLiveData<List<Log>>()
    // viewModel serves for keeping data longer, than activity lives (e.g. when user rotates the screen)
    // activity is destroyed, but viewModel is not, and instead of catching the same data again reuse them
    fun getLogs(realm: RealmEnum, name: String, limit: Int = 0): LiveData<List<Log>> {
        viewModelScope.launch {
            //TODO: error logging
            logs.value = service.getPlayerRaidLogs(realm, name, limit).response.logs
        }
        return logs
    }

    fun getLogs(): LiveData<List<Log>> {
        return logs
    }

    fun getWeeks(logs: List<Log>): LiveData<List<Week>> {
        val weeksData = MutableLiveData<List<Week>>()
        viewModelScope.launch {
            val weeks = mutableListOf<Week>()
            var week = Week(logs[0].killtime)
            var weekStart = week.startDate
            weeks.add(week)
            for (log in logs) {
                if (log.killtime < weekStart.toEpochSecond(ZoneOffset.UTC)) {
                    week = Week(log.killtime)
                    weekStart = week.startDate
                    weeks.add(week)
                }
                week.logs.add(log)
            }
            weeksData.value = weeks
        }
        return weeksData
    }
}
