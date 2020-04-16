package com.example.stackexchangeapp.business.interactor

import org.koin.dsl.module

val interactorModule = module {
    single { StackExchangeInteractor(get()) as IStackExchangeInteractor }
}