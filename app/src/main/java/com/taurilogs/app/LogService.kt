package com.taurilogs.app

import androidx.lifecycle.MutableLiveData
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.models.ServiceResponse
import com.taurilogs.app.models.log.Log

class LogService(private val service: TauriWebService) {

    val logs = MutableLiveData<List<Log>>()

    suspend fun getPlayerRaidLogs(realm: RealmEnum, name: String, limit: Int = 0): ServiceResponse {
        val response = service.getPlayerRaidLogs(realm, name, limit)
        logs.value = response.response.logs
        return ServiceResponse(response.success, response.errorstring)
    }
}
