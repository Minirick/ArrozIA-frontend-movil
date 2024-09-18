package com.example.loginapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginapp.R
import com.example.loginapp.controller.RolesController
import com.example.loginapp.model.Role

// Hacer que RolesActivity herede de BaseActivity para reutilizar el DrawerLayout
class RolesActivity : BaseActivity() {

    private lateinit var rolesController: RolesController
    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var nameEditText: EditText
    private var selectedRole: Role? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Reutilizamos el layout que tiene el DrawerLayout
        setContentView(R.layout.activity_roles)

        // Configurar el DrawerLayout y NavigationView
        setupDrawerMenu()

        // Inicializar el controlador de roles y las vistas
        rolesController = RolesController()

        addButton = findViewById(R.id.addButton)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        nameEditText = findViewById(R.id.nameEditText)

        // Configuración de los botones de añadir, actualizar y eliminar
        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            if (name.isNotEmpty()) {
                val newRole = Role(id = generateId(), name = name)
                rolesController.addRole(newRole)
                clearFields()
                Toast.makeText(this, "Rol añadido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        updateButton.setOnClickListener {
            val name = nameEditText.text.toString()
            if (selectedRole != null && name.isNotEmpty()) {
                selectedRole?.name = name
                rolesController.updateRole(selectedRole!!)
                clearFields()
                Toast.makeText(this, "Rol actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selecciona un rol y completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            if (selectedRole != null) {
                rolesController.deleteRole(selectedRole!!.id)
                clearFields()
                Toast.makeText(this, "Rol eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selecciona un rol para eliminar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para generar un ID único para un nuevo rol
    private fun generateId(): Int {
        return rolesController.getRoles().size + 1
    }

    // Función para limpiar los campos después de agregar, actualizar o eliminar un rol
    private fun clearFields() {
        nameEditText.text.clear()
        selectedRole = null
    }
}
