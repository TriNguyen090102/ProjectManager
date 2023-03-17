package com.example.projectmanager.utils.Validation.use_case

import com.example.projectmanager.utils.Validation.use_case.ValidationResult

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }

        return ValidationResult(successful = true)
    }
}