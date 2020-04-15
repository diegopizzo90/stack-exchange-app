package com.example.stackexchangeapp.business.network.service

import com.example.stackexchangeapp.business.network.model.User
import com.example.stackexchangeapp.business.network.model.UsersResponse
import com.example.stackexchangeapp.business.network.service.StackExchangeService.Companion.PAGE
import com.example.stackexchangeapp.business.network.service.StackExchangeService.Companion.PAGE_SIZE
import com.example.stackexchangeapp.business.network.service.StackExchangeService.Companion.SITE_NAME
import com.example.stackexchangeapp.business.network.service.StackExchangeService.Companion.USERS_PATH
import io.appflate.restmock.JVMFileParser
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.RESTMockServerStarter
import io.appflate.restmock.utils.RequestMatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class StackExchangeServiceTest {

    private lateinit var service: StackExchangeService

    @Before
    fun setUp() {
        RESTMockServerStarter.startSync(JVMFileParser())
        val retrofit = Retrofit.Builder()
            .baseUrl(RESTMockServer.getUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(StackExchangeService::class.java)
    }

    @Test
    fun getUsers_retrieveListOfUsers_assertEqualsTrue() {
        //arrange
        RESTMockServer.whenGET(RequestMatchers.pathContains(USERS_PATH))
            .thenReturnFile(200, RESPONSE_FILE_PATH)
        //act
        val resultResponse = service.getUsers(
            PAGE,
            PAGE_SIZE,
            OrderByValue.ASC.value,
            SortByValue.NAME.value,
            SITE_NAME
        ).test().assertComplete()
        //assert
        resultResponse.assertValue { it == responseModel }
    }

    @After
    fun tearDown() {
        RESTMockServer.shutdown()
    }

    companion object {
        private const val RESPONSE_FILE_PATH = "api-response/response.json"
        private val responseModel = UsersResponse(listOf(User(11, "Agra")))
    }
}