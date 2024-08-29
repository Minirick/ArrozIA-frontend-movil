package com.example.loginapp.controller

import com.example.loginapp.model.Permission

class PermissionsController {

    private val permissions = mutableListOf<Permission>()

    fun getAllPermissions(): List<Permission> {
        return permissions
    }

    fun addPermission(permission: Permission) {
        permissions.add(permission)
    }

    fun updatePermission(permission: Permission) {
        val existingPermission = permissions.find { it.id == permission.id }
        existingPermission?.let {
            it.name = permission.name
            it.description = permission.description
        }
    }

    fun deletePermission(permissionId: Int) {
        permissions.removeIf { it.id == permissionId }
    }
}
