package com.example.stackexchangeapp.ui.mainscreen

import com.example.stackexchangeapp.business.dataviewmodel.UserView

sealed class MainScreenStateView {
    object ShowLoading : MainScreenStateView()
    object HideLoading : MainScreenStateView()
    class ShowData(val data: List<UserView>) : MainScreenStateView()
    object ShowError : MainScreenStateView()
    object ShowEmptyResult : MainScreenStateView()
    object UsernameEmpty : MainScreenStateView()
}