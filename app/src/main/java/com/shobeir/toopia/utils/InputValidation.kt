package com.shobeir.toopia.utils

import androidx.compose.runtime.remember
import androidx.core.text.isDigitsOnly
import com.shobeir.toopia.data.model.ModelPish

object InputValidation {

    fun isValidPhoneNumber(input: String): Boolean {
        return input.isNotEmpty()
                && input.isNotBlank()
                && input.isDigitsOnly()
                && input.startsWith("09")
                && input.length == 11
    }

    fun isValidEmail(input: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }

    fun isValidPassword(input: String): Boolean {
        return input.isNotEmpty() && input.isNotBlank() && input.length >= 6
    }


}

