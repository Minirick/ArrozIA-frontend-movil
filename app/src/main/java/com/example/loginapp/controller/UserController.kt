package com.example.loginapp.controller

import com.example.loginapp.model.User

class UserController {

    private val users = mutableListOf(
        User(1, "John Doe", "john@example.com", "Admin"),
        User(2, "Jane Smith", "jane@example.com", "Editor"),
        User(3, "Jim Brown", "jim@example.com", "Viewer")
    )

    fun addUser(user: User) {
        users.add(user)
    }

    fun updateUser(user: User) {
        users.find { it.id == user.id }?.apply {
            name = user.name
            email = user.email
            role = user.role
        }
    }

    fun deleteUser(userId: Int) {
        users.removeIf { it.id == userId }
    }

    fun getUsers(): List<User> {
        return users
    }

    fun getUserById(userId: Int): User? {
        return users.find { it.id == userId }
    }
}
