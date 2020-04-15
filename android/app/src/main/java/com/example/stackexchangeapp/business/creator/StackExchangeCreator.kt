package com.example.stackexchangeapp.business.creator

import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.network.model.UsersResponse

object StackExchangeCreator {
    fun fromModelToDataViewModel(usersResponse: UsersResponse): List<UserView> {
        return usersResponse.items.map { UserView(it.reputation.toString(), it.userName) }
    }
}