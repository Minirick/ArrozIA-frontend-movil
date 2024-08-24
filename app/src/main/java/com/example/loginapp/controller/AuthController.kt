package com.example.loginapp.controller

import android.content.Context
import android.content.Intent
import com.example.loginapp.view.HomeActivity
import com.example.loginapp.view.LoginActivity

class AuthController(private val context: Context) {

    // Lógica para iniciar sesión
    fun login(username: String, password: String): Boolean {
        val correctUsername = "admin"
        val correctPassword = "1234"

        return if (username == correctUsername && password == correctPassword) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
            true
        } else {
            false
        }
    }

    // Lógica para cerrar sesión
    fun logout() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
    }
}
