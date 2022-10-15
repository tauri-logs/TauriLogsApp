package com.taurilogs.app

import androidx.lifecycle.MutableLiveData
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.enums.RealmEnum
import com.taurilogs.app.models.ServiceResponse
import com.taurilogs.app.models.log.Log
import com.taurilogs.app.models.log.RaidDetail

class LogService(private val service: TauriWebService) {

    var logs: List<Log>? = null
    var raidDetail: RaidDetail? = null
    var realm: RealmEnum? = null

    suspend fun getPlayerRaidLogs(realm: RealmEnum, name: String, limit: Int = 0): ServiceResponse {
        val response = service.getPlayerRaidLogs(realm, name, limit)
        logs = response.response.logs
        return ServiceResponse(response.success, response.errorstring)
    }
    suspend fun getLogDetails(logId: Long, realm: RealmEnum = this.realm!!): ServiceResponse {
        val response = service.getLogDetails(logId, realm)
        raidDetail = response.response
        return ServiceResponse(response.success, response.errorstring)
    }
}
