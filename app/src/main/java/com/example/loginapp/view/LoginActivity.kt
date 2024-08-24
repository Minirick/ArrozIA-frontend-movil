package com.example.loginapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a los elementos de la UI
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Credenciales temporales para la autenticación
        val correctUsername = "admin"
        val correctPassword = "password123"

        // Manejar el evento de clic del botón de iniciar sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if (username == correctUsername && password == correctPassword) {
                // Redirigir a HomeActivity si las credenciales son correctas
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish() // Cierra la LoginActivity para que no vuelva al presionar atrás
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

        // Manejar el evento de clic del botón de recuperar contraseña
        /*forgotPasswordButton.setOnClickListener {
            showForgotPasswordDialog()
        }*/
    }

    private fun showForgotPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Recuperar Contraseña")

        // Configurar el campo de entrada de correo electrónico
        val input = EditText(this)
        input.hint = "Ingrese su correo electrónico"
        builder.setView(input)

        // Configurar los botones de la alerta
        builder.setPositiveButton("Enviar") { dialog, _ ->
            val email = input.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí iría la lógica para enviar el token de recuperación de contraseña al correo
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