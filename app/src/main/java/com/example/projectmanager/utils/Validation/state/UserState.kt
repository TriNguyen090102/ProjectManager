package com.example.projectmanager.utils.Validation.state

sealed class UserState{
    object alreadyLogin :UserState()
    object notYetLogin : UserState()

}
