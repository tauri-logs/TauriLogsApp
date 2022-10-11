package com.taurilogs.app

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.models.Week
import com.taurilogs.app.models.log.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.time.ZoneOffset
import kotlin.system.measureTimeMillis

class TauriViewModel(context: Context, private val service: TauriWebService) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
        android.util.Log.d("Tauri Logs", "Coroutine exception ", exception)
    }

    private val logs = MutableLiveData<List<Log>>()
    // viewModel serves for keeping data longer, than activity lives (e.g. when user rotates the screen)
    // activity is destroyed, but viewModel is not, and instead of catching the same data again reuse them
    fun getLogs(realm: RealmEnum, name: String, limit: Int = 0): LiveData<List<Log>> {
        viewModelScope.launch(errorHandler) {
            val timeInMilis = measureTimeMillis {
                val response = service.getPlayerRaidLogs(realm, name, limit)
                if (!response.success) {throw Exception(response.errorstring)}
                logs.value = response.response.logs
            }
            android.util.Log.d("TauriViewModel", "Player logs retrieved in: $timeInMilis")
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
