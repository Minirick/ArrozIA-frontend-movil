package com.example.loginapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.loginapp.R
import com.example.loginapp.controller.PermissionsController
import com.example.loginapp.model.Permission

class PermissionsActivity : BaseActivity() { // Heredar de BaseActivity

    private lateinit var permissionsController: PermissionsController
    private lateinit var permissionListView: ListView
    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var descriptionEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        // Inicializar el DrawerLayout y NavigationView
        setupDrawer()

        // Inicializar controlador y vistas
        permissionsController = PermissionsController()
        permissionListView = findViewById(R.id.permissionListView)
        addButton = findViewById(R.id.addButton)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        nameEditText = findViewById(R.id.nameEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)

        loadPermissions()

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()
            if (name.isNotEmpty() && description.isNotEmpty()) {
                val newPermission = Permission(id = generateId(), name = name, description = description)
                permissionsController.addPermission(newPermission)
                loadPermissions()
                clearFields()
                Toast.makeText(this, "Permiso añadido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        updateButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val selectedPermission = getSelectedPermission()
            if (selectedPermission != null && name.isNotEmpty() && description.isNotEmpty()) {
                selectedPermission.name = name
                selectedPermission.description = description
                permissionsController.updatePermission(selectedPermission)
                loadPermissions()
                clearFields()
                Toast.makeText(this, "Permiso actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selecciona un permiso y completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            val selectedPermission = getSelectedPermission()
            if (selectedPermission != null) {
                permissionsController.deletePermission(selectedPermission.id)
                loadPermissions()
                clearFields()
                Toast.makeText(this, "Permiso eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selecciona un permiso para eliminar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadPermissions() {
        val permissions = permissionsController.getAllPermissions()
        // Aquí debes conectar el ListView con un adaptador para mostrar los permisos
    }

    private fun getSelectedPermission(): Permission? {
        return null // Implementar la lógica para obtener el permiso seleccionado
    }

    private fun generateId(): Int {
        return permissionsController.getAllPermissions().size + 1
    }

    private fun clearFields() {
        nameEditText.text.clear()
        descriptionEditText.text.clear()
    }
}
