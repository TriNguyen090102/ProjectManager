package com.example.projectmanager.Models

import kotlinx.serialization.Serializable


data class User(
    val id: String = "",
    val email: String = "",

    val name: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val fcmToken: String = ""
)