package com.example.loginapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.controller.UserController // Asegúrate de que esta línea esté presente
import com.example.loginapp.model.User
import com.example.loginapp.view.adapter.UserAdapter

class DashboardActivity : AppCompatActivity() {

    private lateinit var usersController: UserController
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inicializar controlador de usuarios
        usersController = UserController()

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener la lista de usuarios y configurar el adaptador
        val usersList: List<User> = usersController.getUsers()
        userAdapter = UserAdapter(usersList)
        recyclerView.adapter = userAdapter
    }
}
