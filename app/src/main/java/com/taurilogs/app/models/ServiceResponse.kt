package com.taurilogs.app.models

data class ServiceResponse(
    val success: Boolean,
    val errorMessage: String? = null
) {
}
