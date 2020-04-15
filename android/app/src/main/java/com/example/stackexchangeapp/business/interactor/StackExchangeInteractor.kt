package com.example.stackexchangeapp.business.interactor

import com.example.stackexchangeapp.business.creator.StackExchangeCreator
import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.network.service.OrderByValue
import com.example.stackexchangeapp.business.network.service.SortByValue
import com.example.stackexchangeapp.business.network.service.StackExchangeService
import io.reactivex.Single

class StackExchangeInteractor(private val service: StackExchangeService) :
    IStackExchangeInteractor {

    override fun getUsers(
        page: Int,
        pageSize: Int,
        orderBy: OrderByValue,
        sortBy: SortByValue
    ): Single<List<UserView>> {
        return service.getUsers(page, pageSize, orderBy.value, sortBy.value)
            .map { StackExchangeCreator.fromModelToDataViewModel(it) }
    }
}