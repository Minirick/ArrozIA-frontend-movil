package com.example.loginapp.utils

import org.junit.Assert.*
import org.junit.Test

class LoginValidatorTest {

    private val loginValidator = LoginValidator()

    @Test
    fun validateCredentials_correctCredentials_returnTrue() {
        val result = loginValidator.validateCredentials("admin", "password123")
        assertTrue(result)
    }

    @Test
    fun validateCredentials_incorrectUsername_returnFalse() {
        val result = loginValidator.validateCredentials("wrong_user", "password123")
        assertFalse(result)
    }

    @Test
    fun validateCredentials_incorrectPassword_returnFalse() {
        val result = loginValidator.validateCredentials("admin", "wrong_password")
        assertFalse(result)
    }

    @Test
    fun validateCredentials_emptyCredentials_returnFalse() {
        val result = loginValidator.validateCredentials("", "")
        assertFalse(result)
    }
}
