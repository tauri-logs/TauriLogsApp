package com.taurilogs.app

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.models.ServiceResponse
import com.taurilogs.app.models.log.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class SearchViewModel(private val service: LogService) : ViewModel() {

    val searchFinished: MutableLiveData<ServiceResponse> = MutableLiveData()

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        searchFinished.value = ServiceResponse(false, exception.message ?: "Unknown error")
    }

    // viewModel serves for keeping data longer, than activity lives (e.g. when user rotates the screen)
    // activity is destroyed, but viewModel is not, and instead of catching the same data again reuse them
    fun fetchLogs(realm: RealmEnum, name: String, limit: Int = 0) {
        android.util.Log.d("Tauri Logs", "fetchLogs ${viewModelScope.isActive}")
        viewModelScope.launch(errorHandler) {
            android.util.Log.d("Tauri Logs", "fetchLogs coroutine")
            val timeInMilis = measureTimeMillis {
                searchFinished.value = ServiceResponse(false)
                searchFinished.value = service.getPlayerRaidLogs(realm, name, limit)
            }
            android.util.Log.d("TauriViewModel", "Player logs retrieved in: $timeInMilis")
        }
    }
}
