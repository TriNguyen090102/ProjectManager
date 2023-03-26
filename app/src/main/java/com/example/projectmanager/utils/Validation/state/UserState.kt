package com.example.projectmanager.utils.Validation.state

import com.example.projectmanager.Models.User
import kotlinx.coroutines.flow.MutableStateFlow

sealed class UserState {
    data class alreadyLogin(val data: User) : UserState()
    object notYetLogin : UserState()
}
