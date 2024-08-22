package com.example.loginapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.controller.RolesController
import com.example.loginapp.model.Role

class RolesActivity : AppCompatActivity() {

    private lateinit var rolesController: RolesController
    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var nameEditText: EditText
    private var selectedRole: Role? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roles)

        rolesController = RolesController()

        addButton = findViewById(R.id.addButton)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        nameEditText = findViewById(R.id.nameEditText)

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

    private fun generateId(): Int {
        // Generar un ID único para el nuevo rol
        return rolesController.getRoles().size + 1
    }

    private fun clearFields() {
        nameEditText.text.clear()
        selectedRole = null
    }
}
