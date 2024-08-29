package com.example.loginapp.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.R
import com.example.loginapp.controller.RolesController
import com.example.loginapp.model.Role

class RolesActivity : AppCompatActivity() {

    private lateinit var rolesController: RolesController
    private lateinit var roleListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roles)

        rolesController = RolesController(this)
        roleListView = findViewById(R.id.roleListView)
        loadRoles()

        val addButton: Button = findViewById(R.id.addRoleButton)
        val updateButton: Button = findViewById(R.id.updateRoleButton)
        val deleteButton: Button = findViewById(R.id.deleteRoleButton)
        val nameEditText: EditText = findViewById(R.id.roleNameEditText)
        val descriptionEditText: EditText = findViewById(R.id.roleDescriptionEditText)
        val backButton: Button = findViewById(R.id.button_back_to_home)

        addButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()
            if (name.isNotEmpty() && description.isNotEmpty()) {
                val newRole = Role(id = generateId(), name = name, description = description)
                rolesController.addRole(newRole)
                loadRoles()
                clearFields()
                Toast.makeText(this, "Rol añadido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        updateButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val selectedRole = getSelectedRole()
            if (selectedRole != null && name.isNotEmpty() && description.isNotEmpty()) {
                selectedRole.name = name
                selectedRole.description = description
                rolesController.updateRole(selectedRole)
                loadRoles()
                clearFields()
                Toast.makeText(this, "Rol actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selecciona un rol y completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            val selectedRole = getSelectedRole()
            if (selectedRole != null) {
                rolesController.deleteRole(selectedRole.id)
                loadRoles()
                clearFields()
                Toast.makeText(this, "Rol eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selecciona un rol para eliminar", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            finish() // Cierra la actividad y vuelve a la pantalla anterior (Inicio)
        }
    }

    private fun loadRoles() {
        val roles = rolesController.getRoles()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, roles)
        roleListView.adapter = adapter
        roleListView.choiceMode = ListView.CHOICE_MODE_SINGLE
    }

    private fun clearFields() {
        findViewById<EditText>(R.id.roleNameEditText).text.clear()
        findViewById<EditText>(R.id.roleDescriptionEditText).text.clear()
    }

    private fun getSelectedRole(): Role? {
        val position = roleListView.checkedItemPosition
        return if (position != ListView.INVALID_POSITION) {
            roleListView.getItemAtPosition(position) as Role
        } else {
            null
        }
    }

    private fun generateId(): Int {
        return rolesController.getRoles().size + 1 // Simplemente incrementa el tamaño de la lista
    }
}
