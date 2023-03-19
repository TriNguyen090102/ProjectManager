package com.example.projectmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.projectmanager.utils.Validation.state.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel :ViewModel() {

    private val userState = MutableStateFlow<UserState>(UserState.notYetLogin)
    val user = userState.asStateFlow()
}

