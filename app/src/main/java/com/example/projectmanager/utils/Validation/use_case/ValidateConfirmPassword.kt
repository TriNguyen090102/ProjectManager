package com.example.projectmanager.utils.Validation.use_case

import com.example.projectmanager.utils.Validation.use_case.ValidationResult

class ValidateConfirmPassword {
    fun execute(password: String, comfirmedPassword: String): ValidationResult {
        if (password != comfirmedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The confirm password does not match"
            )
        }

        return ValidationResult(successful = true)
    }
}