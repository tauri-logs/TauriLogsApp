package com.taurilogs.app.viewmodels

import androidx.lifecycle.viewModelScope
import com.taurilogs.app.services.LogService
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.models.ServiceResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class SearchViewModel(private val service: LogService) : CustomViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        android.util.Log.d("Tauri Logs", "Coroutine exception ", exception)
        val message = when (exception.message) {
            "HTTP 502 Bad Gateway" -> "Tauri server is currently unavailable. Try again later."
            else -> exception.message ?: "Unknown error"
        }
        searchFinished.value = ServiceResponse(false, message)
    }

    // viewModel serves for keeping data longer, than activity lives (e.g. when user rotates the screen)
    // activity is destroyed, but viewModel is not, and instead of catching the same data again reuse them
    fun fetchLogs(realm: RealmEnum, name: String, limit: Int = 0) {
        android.util.Log.d("Tauri Logs", "fetchLogs ${viewModelScope.isActive}")
        viewModelScope.launch(errorHandler) {
            android.util.Log.d("Tauri Logs", "fetchLogs coroutine")
            val timeInMilis = measureTimeMillis {
                service.realm = realm
                searchFinished.value = ServiceResponse(false)
                searchFinished.value = service.getPlayerRaidLogs(realm, name, limit)
            }
            android.util.Log.d("TauriViewModel", "Player logs retrieved in: $timeInMilis")
        }
    }
}
