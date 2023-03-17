package com.example.projectmanager.utils.Validation.use_case

import android.util.Patterns

class ValidateName {
    fun execute(name: String): ValidationResult {
        if (name.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The name can not be blank"
            )
        }
        return ValidationResult(successful = true)
    }
}