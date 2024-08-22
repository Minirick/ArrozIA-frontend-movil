package com.example.loginapp.controller
import com.example.loginapp.model.User

class UserController {

    private val users = mutableListOf(
        User(1, "Alice", "alice@example.com", 1),
        User(2, "Bob", "bob@example.com", 2),
        User(3, "Charlie", "charlie@example.com", 3)
    )

    fun addUser(user: User) {
        // Añadir un nuevo usuario
        users.add(user)
    }

    fun updateUser(user: User) {
        // Buscar el usuario por su ID y actualizarlo
        users.find { it.id == user.id }?.apply {
            this.name = user.name
            this.email = user.email
            this.roleId = user.roleId
        }
    }

    fun deleteUser(userId: Int) {
        // Eliminar el usuario por su ID
        users.removeIf { it.id == userId }
    }

    fun getUsers(): List<User> {
        // Devolver la lista de usuarios
        return users
    }
}
