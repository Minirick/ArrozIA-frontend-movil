package com.example.loginapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.utils.LoginValidator

class LoginActivity : AppCompatActivity() {

    private val loginValidator = LoginValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if (loginValidator.validateCredentials(username, password)) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
        val forgotPasswordButton = findViewById<Button>(R.id.forgotPasswordButton)
        // Recuperar contraseña
        forgotPasswordButton.setOnClickListener {
            showForgotPasswordDialog()
        }
    }

    private fun showForgotPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Recuperar Contraseña")

        val input = EditText(this)
        input.hint = "Ingrese su correo electrónico"
        builder.setView(input)

        builder.setPositiveButton("Enviar") { dialog, _ ->
            val email = input.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Se ha enviado un correo para restablecer la contraseña", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
}
