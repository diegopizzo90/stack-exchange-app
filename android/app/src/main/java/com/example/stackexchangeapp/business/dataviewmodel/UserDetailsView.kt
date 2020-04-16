package com.example.stackexchangeapp.business.dataviewmodel

data class UserDetailsView(
    val imageUrl: String,
    val userName: String,
    val reputation: String,
    val badgesBronze: String,
    val badgesSilver: String,
    val badgesGold: String,
    val location: String?,
    val age: String?,
    val creationDate: String
)