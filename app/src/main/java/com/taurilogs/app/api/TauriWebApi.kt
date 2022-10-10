package com.taurilogs.app.api

import com.taurilogs.app.api.responses.PlayerRaidResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TauriWebApi {
    @POST("apiIndex.php")
    suspend fun getPlayerRaidLogs(@Body apiRequest: ApiRequest): ApiResponse<PlayerRaidResponse>

}
