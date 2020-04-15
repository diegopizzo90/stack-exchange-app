package com.example.stackexchangeapp.ui.mainscreen.viewmodel

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainScreenViewModel(get()) }
}