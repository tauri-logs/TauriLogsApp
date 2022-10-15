package com.taurilogs.app.api

import com.taurilogs.app.api.responses.PlayerRaidResponse
import com.taurilogs.app.models.log.RaidDetail
import retrofit2.http.Body
import retrofit2.http.POST

interface TauriWebApi {
    @POST("apiIndex.php")
    suspend fun getPlayerRaidLogs(@Body apiRequest: ApiRequest): ApiResponse<PlayerRaidResponse>

    @POST("apiIndex.php")
    suspend fun getLogDetails(@Body apiRequest: ApiRequest): ApiResponse<RaidDetail>

}
