package com.example.projectmanager.Models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    @Transient val id: String = "",
    @Transient val email: String = "",

    @Transient val name: String = "",
    @Transient val image: String = "",
    @Transient val mobile: Long = 0,
    @Transient val fcmToken: String = ""
)