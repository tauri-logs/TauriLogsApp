package com.taurilogs.app

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taurilogs.app.models.Week
import com.taurilogs.app.models.log.Log
import com.taurilogs.app.ui.WeekListAdapter
import kotlinx.coroutines.launch
import java.time.ZoneOffset

class PlayerViewModel(private val service: LogService) : ViewModel() {

    private val weekListAdapter: MutableLiveData<WeekListAdapter> = MutableLiveData()

    private fun getWeeks(logs: List<Log>): List<Week> {
        android.util.Log.d("TauriViewModel", "getWeeks")
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
        return weeks
    }

    fun getWeekListAdapter(owner: Context): MutableLiveData<WeekListAdapter> {
        viewModelScope.launch {
            if (weekListAdapter.value == null && service.logs.value != null) {
                weekListAdapter.value = WeekListAdapter(owner, getWeeks(service.logs.value!!))
            }
        }
        return weekListAdapter
    }

    override fun onCleared() {
        super.onCleared()
        android.util.Log.d("PlayerViewModel", "onCleared")
    }
}
