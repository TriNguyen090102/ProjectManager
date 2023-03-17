package com.example.projectmanager.utils.Validation.event

import com.example.projectmanager.utils.Validation.state.ValidationState

sealed class ValidationEvent {

    object Submit : ValidationEvent()


    data class EmailChanged(val email: String) : ValidationEvent()
    data class PasswordChanged(val password: String) : ValidationEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : ValidationEvent()
    data class NameChanged(val name: String) : ValidationEvent()
}
