package com.example.loginapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.controller.PermissionsController
import com.example.loginapp.model.Permission

class PermissionsActivity : AppCompatActivity() {

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

        permissionsController = PermissionsController()

        permissionListView = findViewById(R.id.permissionListView)
        addButton = findViewById(R.id.addButton)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        nameEditText = findViewById(R.id.nameEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)

        // Inicializar la lista de permisos
        loadPermissions()

        // Dentro de onCreate
        val backButton: Button = findViewById(R.id.button_back_to_home)
        backButton.setOnClickListener {
            finish()  // Volver a la actividad anterior
        }


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
        // Cargar los permisos en el ListView
        val permissions = permissionsController.getAllPermissions()
        // Aquí debes conectar el ListView con un adaptador para mostrar los permisos
    }

    private fun getSelectedPermission(): Permission? {
        // Obtener el permiso seleccionado en la lista
        // Implementa esta función para obtener el permiso que el usuario ha seleccionado en el ListView
        return null // Cambia esto para que devuelva el permiso seleccionado
    }

    private fun generateId(): Int {
        // Generar un ID único para el nuevo permiso
        return permissionsController.getAllPermissions().size + 1
    }

    private fun clearFields() {
        nameEditText.text.clear()
        descriptionEditText.text.clear()
    }
}
