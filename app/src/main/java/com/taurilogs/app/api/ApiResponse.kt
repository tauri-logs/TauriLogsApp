package com.taurilogs.app.api

data class ApiResponse<T>(
    val success: Boolean,
    val errorcode: Int,
    val errorstring: String,
    val response: T
)
