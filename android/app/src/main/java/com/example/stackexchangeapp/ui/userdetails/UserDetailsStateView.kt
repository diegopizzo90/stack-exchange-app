package com.example.stackexchangeapp.ui.userdetails

import com.example.stackexchangeapp.business.dataviewmodel.UserDetailsView

sealed class UserDetailsStateView {
    object ShowLoading : UserDetailsStateView()
    object HideLoading : UserDetailsStateView()
    class ShowData(val data: UserDetailsView) : UserDetailsStateView()
    object ShowError : UserDetailsStateView()
}