package com.example.loginapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.controller.UserController

class UserActivity : AppCompatActivity() {

    private lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userController = UserController()

        // Aquí puedes inicializar la lógica para manejar la lista de usuarios, por ejemplo, cargando los usuarios desde el controlador
    }
}
