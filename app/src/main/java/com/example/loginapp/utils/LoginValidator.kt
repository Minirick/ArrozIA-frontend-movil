package com.example.loginapp.utils

class LoginValidator {
    fun validateCredentials(username: String, password: String): Boolean {
        val correctUsername = "admin"
        val correctPassword = "password123"
        return username == correctUsername && password == correctPassword
    }
}
