package com.example.stackexchangeapp.business.network

import com.example.stackexchangeapp.business.network.service.StackExchangeService
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { providesRetrofit() }
    single { providesStackExchangeService(get()) }
}

fun providesRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(StackExchangeService.createBaseURL())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun providesStackExchangeService(retrofit: Retrofit): StackExchangeService {
    return retrofit.create(StackExchangeService::class.java)
}