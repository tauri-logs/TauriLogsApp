package com.taurilogs.app.ui

import androidx.appcompat.app.AppCompatActivity
import com.taurilogs.app.viewmodels.CustomViewModel

abstract class CustomActivity: AppCompatActivity() {
    abstract val viewModel: CustomViewModel
    abstract val nextDestination: Class<*>

}
