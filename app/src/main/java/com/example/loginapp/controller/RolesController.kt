package com.example.loginapp.controller

import com.example.loginapp.model.Role

class RolesController {

    private val roles = mutableListOf(
        Role(1, "Administrator"),
        Role(2, "Editor"),
        Role(3, "Viewer")
    )

    fun addRole(role: Role) {
        // Añadir un nuevo rol
        roles.add(role)
    }

    fun updateRole(role: Role) {
        // Buscar el rol por su ID y actualizarlo
        roles.find { it.id == role.id }?.apply {
            this.name = role.name
        }
    }

    fun deleteRole(roleId: Int) {
        // Eliminar el rol por su ID
        roles.removeIf { it.id == roleId }
    }

    fun getRoles(): List<Role> {
        // Devolver la lista de roles
        return roles
    }
}
