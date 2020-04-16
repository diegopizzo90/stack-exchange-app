package com.example.stackexchangeapp.business.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserDetails(
    @SerializedName("profile_image") val imageUrl: String,
    @SerializedName("display_name") val userName: String,
    val reputation: String,
    val badges: Badges,
    val location: String?,
    val age: Int?,
    val creationDate: Date
)