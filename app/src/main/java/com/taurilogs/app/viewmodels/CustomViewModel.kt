package com.taurilogs.app.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taurilogs.app.models.ServiceResponse

abstract class CustomViewModel: ViewModel() {
    val searchFinished: MutableLiveData<ServiceResponse> = MutableLiveData()
}
