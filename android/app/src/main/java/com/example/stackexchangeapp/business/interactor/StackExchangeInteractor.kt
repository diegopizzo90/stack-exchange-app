package com.example.stackexchangeapp.business.interactor

import com.example.stackexchangeapp.business.creator.StackExchangeCreator
import com.example.stackexchangeapp.business.dataviewmodel.UserDetailsView
import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.network.service.OrderByValue
import com.example.stackexchangeapp.business.network.service.SortByValue
import com.example.stackexchangeapp.business.network.service.StackExchangeService
import io.reactivex.Single

class StackExchangeInteractor(private val service: StackExchangeService) :
    IStackExchangeInteractor {

    override fun getUsersByName(
        page: Int,
        pageSize: Int,
        orderBy: OrderByValue,
        sortBy: SortByValue,
        name: String
    ): Single<List<UserView>> {
        return service.getUsers(page, pageSize, orderBy.value, sortBy.value, nameQuery = name)
            .map { StackExchangeCreator.fromModelToDataViewModel(it) }
    }

    override fun getUserDetailsById(userId: Int): Single<UserDetailsView> {
        return service.getUser(userId).map { StackExchangeCreator.fromModelToDataViewModel(it) }
    }
}