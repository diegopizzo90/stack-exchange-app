package com.example.stackexchangeapp.business.network.model

import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("profile_image") val imageUrl: String,
    @SerializedName("display_name") val userName: String,
    val reputation: String,
    @SerializedName("badge_counts") val badges: Badges,
    val location: String?,
    val age: Int?,
    @SerializedName("creation_date") val creationDate: Long
)