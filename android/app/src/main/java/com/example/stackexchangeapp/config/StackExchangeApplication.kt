package com.example.stackexchangeapp.config

import android.app.Application
import com.example.stackexchangeapp.business.interactor.interactorModule
import com.example.stackexchangeapp.business.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StackExchangeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StackExchangeApplication)
            modules(listOf(networkModule, interactorModule))
        }
    }
}