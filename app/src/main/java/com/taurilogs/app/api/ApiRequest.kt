package com.taurilogs.app.api

data class ApiRequest(
    val secret: String,
    val url: String,
    val params: RequestParams
) {
}
