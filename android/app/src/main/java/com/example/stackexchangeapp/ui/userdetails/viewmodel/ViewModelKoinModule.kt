package com.example.stackexchangeapp.ui.userdetails.viewmodel

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userDetailsViewModelModule = module {
    viewModel { UserDetailsViewModel(get()) }
}