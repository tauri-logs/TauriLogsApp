package com.taurilogs.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.models.log.Log
import kotlinx.coroutines.launch

class TauriViewModel(private val service: TauriWebService) : ViewModel() {

    // viewModel serves for keeping data longer, than activity lives (e.g. when user rotates the screen)
    // activity is destroyed, but viewModel is not, and instead of catching the same data again reuse them
    fun getLogs(realm: RealmEnum, name: String, limit: Int = 0): LiveData<List<Log>> {
        val data = MutableLiveData<List<Log>>()
        viewModelScope.launch {
            //TODO: error logging
            data.value = service.getPlayerRaidLogs(realm, name, limit).response.logs
        }
        return data
    }
}
