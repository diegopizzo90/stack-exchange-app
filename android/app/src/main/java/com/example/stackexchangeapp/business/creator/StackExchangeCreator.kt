package com.example.stackexchangeapp.business.creator

import com.example.stackexchangeapp.business.dataviewmodel.UserDetailsView
import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.network.model.UserDetailsResponse
import com.example.stackexchangeapp.business.network.model.UsersResponse

object StackExchangeCreator {
    fun fromModelToDataViewModel(usersResponse: UsersResponse): List<UserView> {
        return usersResponse.items.map { UserView(it.id, it.reputation.toString(), it.userName) }
    }

    fun fromModelToDataViewModel(userDetailsResponse: UserDetailsResponse): List<UserDetailsView> {
        return userDetailsResponse.items.map {
            UserDetailsView(
                it.imageUrl,
                it.userName,
                it.reputation,
                it.badges.bronze.toString(),
                it.badges.silver.toString(),
                it.badges.gold.toString(),
                it.location,
                it.age.toString(),
                it.creationDate
            )
        }
    }
}