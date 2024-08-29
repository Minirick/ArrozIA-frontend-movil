package com.example.loginapp.controller

import android.content.Context
import com.example.loginapp.model.Role

class RolesController(context: Context) {
    private val roles = mutableListOf<Role>()

    fun addRole(role: Role) {
        roles.add(role)
    }

    fun updateRole(role: Role) {
        val index = roles.indexOfFirst { it.id == role.id }
        if (index != -1) {
            roles[index] = role
        }
    }

    fun deleteRole(roleId: Int) {
        roles.removeAll { it.id == roleId }
    }

    fun getRoles(): List<Role> {
        return roles
    }
}
