package com.example.stackexchangeapp.business.creator

import com.example.stackexchangeapp.business.dataviewmodel.UserView
import com.example.stackexchangeapp.business.network.model.User
import com.example.stackexchangeapp.business.network.model.UsersResponse
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StackExchangeCreatorTest {

    @Test
    fun fromModelToDataViewModel_() {
        val result = StackExchangeCreator.fromModelToDataViewModel(responseModel)
        assertEquals(result, dataViewModel)
    }

    companion object {
        private val responseModel = UsersResponse(
            listOf(
                User(1233, 12, "username1"),
                User(33231, 22, "username2")
            )
        )

        private val dataViewModel = listOf(
            UserView(1233, "12", "username1"),
            UserView(33231, "22", "username2")
        )
    }
}