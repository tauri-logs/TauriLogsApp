package com.taurilogs.app

import org.koin.dsl.module
import com.taurilogs.app.api.TauriWebService
import com.taurilogs.app.services.LogService
import com.taurilogs.app.services.SettingsService
import com.taurilogs.app.viewmodels.PlayerViewModel
import com.taurilogs.app.viewmodels.SearchViewModel
import com.taurilogs.app.viewmodels.RaidDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf

val applicationModule = module {
    single { TauriWebService(androidContext()) }
    singleOf(::LogService)
    singleOf(::SettingsService)
    viewModelOf(::SearchViewModel)
    viewModelOf(::PlayerViewModel)
    viewModelOf(::RaidDetailViewModel)
}
