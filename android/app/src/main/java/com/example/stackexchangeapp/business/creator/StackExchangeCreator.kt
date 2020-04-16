package com.example.stackexchangeapp.business.creator

import com.example.stackexchangeapp.business.dataviewmodel.UserDetailsView
import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.network.model.UserDetailsResponse
import com.example.stackexchangeapp.business.network.model.UsersResponse

object StackExchangeCreator {
    fun fromModelToDataViewModel(usersResponse: UsersResponse): List<UserView> {
        return usersResponse.items.map { UserView(it.id, it.reputation.toString(), it.userName) }
    }

    fun fromModelToDataViewModel(userDetailsResponse: UserDetailsResponse): UserDetailsView {
        val userDetails = userDetailsResponse.items.first()
        return UserDetailsView(
            userDetails.imageUrl,
            userDetails.userName,
            userDetails.reputation,
            userDetails.badges.bronze.toString(),
            userDetails.badges.silver.toString(),
            userDetails.badges.gold.toString(),
            userDetails.location,
            userDetails.age.toString(),
            userDetails.creationDate
        )
    }
}