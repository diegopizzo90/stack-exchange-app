package com.example.stackexchangeapp.business.network.service

import com.example.stackexchangeapp.business.network.model.UserDetailsResponse
import com.example.stackexchangeapp.business.network.model.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackExchangeService {

    companion object {
        private const val VERSION = "2.2"
        private const val BASE_URL_PATH = "https://api.stackexchange.com"
        const val USERS_PATH = "users"
        const val USER_PATH = "users/{ids}"
        const val PAGE = 1
        const val PAGE_SIZE = 20
        const val SITE_NAME = "stackoverflow"

        //I created this method to pass the version as parameter in order to have a way to change it dynamically
        fun createBaseURL(version: String = VERSION): String {
            return "${BASE_URL_PATH}/${version}/"
        }
    }

    @GET(USERS_PATH)
    fun getUsers(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("order") orderBy: String,
        @Query("sort") sortBy: String,
        @Query("site") siteName: String = SITE_NAME,
        @Query("inname") nameQuery: String
    ): Single<UsersResponse>

    @GET(USER_PATH)
    fun getUser(
        @Path("ids") id: Int,
        @Query("site") siteName: String = SITE_NAME
    ): Single<UserDetailsResponse>
}

enum class OrderByValue(val value: String) {
    ASC("asc"), DESC("desc")
}

enum class SortByValue(val value: String) {
    NAME("name"), CREATION("creation"), REPUTATION("reputation"), MODIFIED("modified")
}