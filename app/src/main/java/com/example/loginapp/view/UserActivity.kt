package com.example.loginapp.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.controller.UserController
import com.example.loginapp.model.User

class UserActivity : AppCompatActivity() {

    private lateinit var userController: UserController
    private lateinit var userListView: ListView
    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var roleEditText: EditText
    private var selectedUserId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userController = UserController()

        userListView = findViewById(R.id.userListView)
        addButton = findViewById(R.id.addButton)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        roleEditText = findViewById(R.id.roleEditText)

        loadUsers()

        // Dentro de onCreate
        val backButton: Button = findViewById(R.id.button_back_to_home)
        backButton.setOnClickListener {
            finish()  // Volver a la actividad anterior
        }

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val role = roleEditText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && role.isNotEmpty()) {
                val newUser = User(id = generateId(), name = name, email = email, role = role)
                userController.addUser(newUser)
                loadUsers()
                clearFields()
                Toast.makeText(this, "Usuario añadido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        updateButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val role = roleEditText.text.toString()

            if (selectedUserId != null && name.isNotEmpty() && email.isNotEmpty() && role.isNotEmpty()) {
                val updatedUser =
                    User(id = selectedUserId!!, name = name, email = email, role = role)
                userController.updateUser(updatedUser)
                loadUsers()
                clearFields()
                Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Selecciona un usuario y completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        deleteButton.setOnClickListener {
            if (selectedUserId != null) {
                userController.deleteUser(selectedUserId!!)
                loadUsers()
                clearFields()
                Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selecciona un usuario para eliminar", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        userListView.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = userController.getUsers()[position]
            selectedUserId = selectedUser.id
            nameEditText.setText(selectedUser.name)
            emailEditText.setText(selectedUser.email)
            roleEditText.setText(selectedUser.role)
        }
    }

    private fun loadUsers() {
        val users = userController.getUsers()
        val userNames = users.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, userNames)
        userListView.adapter = adapter
    }

    private fun generateId(): Int {
        return userController.getUsers().size + 1
    }

    private fun clearFields() {
        nameEditText.text.clear()
        emailEditText.text.clear()
    }
}
