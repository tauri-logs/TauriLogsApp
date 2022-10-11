package com.taurilogs.app.api

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.taurilogs.app.api.responses.PlayerRaidResponse
import com.taurilogs.app.enums.*
import com.taurilogs.app.enums.adapters.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okio.use
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Properties

const val BASE_URL = "http://chapi.tauri.hu/"

class TauriWebService(context: Context, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val properties: Properties by lazy {
        Properties().apply {
            context.assets.open("app.properties").use {
                load(it)
            }
        }
    }

    private val api: TauriWebApi by lazy {
        createTauriWebApi()
    }

    private fun createTauriWebApi(): TauriWebApi {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
            .add(ClassEnumAdapter())
            .add(ExpansionEnumAdapter())
            .add(FactionEnumAdapter())
            .add(GenderEnumAdapter())
            .add(RaceEnumAdapter())
            .add(RealmEnumAdapter())
            .add(SpecEnumAdapter())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val url = chain.request().url
                            .newBuilder()
                            .addQueryParameter("apikey", properties.getProperty("apikey"))
                            .build()
                        chain.proceed(chain.request().newBuilder().url(url).build())
                    }
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()
        return retrofit.create(TauriWebApi::class.java)
    }

    suspend fun getPlayerRaidLogs(realm: RealmEnum, characterName: String, limit: Int = 0): ApiResponse<PlayerRaidResponse> {
        return withContext(dispatcher) {
            api.getPlayerRaidLogs( ApiRequest(properties.getProperty("secret"), "raid-player",
                RequestParams(realm.toString(), characterName, limit, null)
            ))
        }
    }

}
