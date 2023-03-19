package com.example.projectmanager.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmanager.utils.Validation.event.ValidationEvent

import com.example.projectmanager.utils.Validation.state.ValidationState
import com.example.projectmanager.utils.Validation.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class BaseValidationViewModel(
    private val validateName: ValidateName = ValidateName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateConFirmPassword: ValidateConfirmPassword = ValidateConfirmPassword()
)

    : ViewModel() {
    var type: String? = null
    var invalid : Boolean? = null

    var state by mutableStateOf(ValidationState())

    private val resultEventChannel = Channel<EventResult>()
    val validationEvents = resultEventChannel.receiveAsFlow()

    fun onEvent(event: ValidationEvent) {

        when (event) {
            is ValidationEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is ValidationEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is ValidationEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is ValidationEvent.Submit -> {
                submitData()
            }
            is ValidationEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }
        }
    }

    fun submitData() {



        //sign in validation
        if (type == "sign_in") {
            val emailResult: ValidationResult = validateEmail.execute(state.email)
            val passwordResult: ValidationResult = validatePassword.execute(state.password)
            val hasError = listOf(
                emailResult,
                passwordResult,
            ).any { !it.successful }

            invalid = hasError

            if (hasError) {
                state = state.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
                return
            }
            viewModelScope.launch {
                resultEventChannel.send(EventResult.Success)
            }

        }

        //sign up validation
        if (type == "sign_up") {
            val nameResult: ValidationResult = validateName.execute(state.name)
            val emailResult: ValidationResult = validateEmail.execute(state.email)
            val passwordResult: ValidationResult = validatePassword.execute(state.password)
            val repeatedPasswordResult: ValidationResult =
                validateConFirmPassword.execute(state.password, state.repeatedPassword)
            val hasError = listOf(
                nameResult,
                emailResult,
                passwordResult,
                repeatedPasswordResult,
            ).any { !it.successful }

            invalid = hasError

            if (hasError) {
                state = state.copy(
                    nameError = nameResult.errorMessage,
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    repeatedPasswordError = repeatedPasswordResult.errorMessage
                )
                return
            }

            viewModelScope.launch {
                resultEventChannel.send(EventResult.Success)
            }


        }
    }

    sealed class EventResult {
        object Success : EventResult()
    }
}