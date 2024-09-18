package com.example.loginapp.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.R
import com.example.loginapp.controller.UserController
import com.example.loginapp.model.User
import com.example.loginapp.adapter.UserAdapter

class DashboardActivity : BaseActivity() {

    private lateinit var usersController: UserController
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Configurar el DrawerLayout y NavigationView
        setupDrawer()

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
