package com.taurilogs.app

import org.koin.dsl.module
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.viewmodels.PlayerViewModel
import com.taurilogs.app.viewmodels.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf

val applicationModule = module {
    single { TauriWebService(androidContext()) }
    singleOf(::LogService)
    viewModelOf(::SearchViewModel)
    viewModelOf(::PlayerViewModel)
}
