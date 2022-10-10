package com.taurilogs.app

import org.koin.dsl.module
import com.taurilogs.app.api.TauriWebService
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf

val applicationModule = module {
    single { TauriWebService(androidContext()) }
    singleOf(::TauriViewModel)
}
