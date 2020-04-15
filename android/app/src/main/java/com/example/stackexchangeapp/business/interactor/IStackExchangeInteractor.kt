package com.example.stackexchangeapp.business.interactor

import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.network.service.OrderByValue
import com.example.stackexchangeapp.business.network.service.SortByValue
import com.example.stackexchangeapp.business.network.service.StackExchangeService
import io.reactivex.Single

interface IStackExchangeInteractor {

    fun getUsers(
        page: Int = StackExchangeService.PAGE,
        pageSize: Int = StackExchangeService.PAGE_SIZE,
        orderBy: OrderByValue = OrderByValue.ASC,
        sortBy: SortByValue = SortByValue.NAME
    ): Single<List<UserView>>
}