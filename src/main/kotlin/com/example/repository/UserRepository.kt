package com.example.repository

import com.example.data.model.User
import com.example.data.table.UserTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserRepository {
    suspend fun createUser(user: User) {
        dbQuery {
            UserTable.insert { ut ->
                ut[name] = user.userName
                ut[email] = user.email
                ut[hashPassword] = user.hashPassword
            }
        }
    }

    suspend fun findUserByEmail(email: String) = dbQuery {
        UserTable.select { UserTable.email.eq(email) }
            .map { it.toUser() }
            .singleOrNull()
    }

    private fun ResultRow?.toUser(): User? {
        this ?: return null

        return User(
            this[UserTable.email],
            this[UserTable.hashPassword],
            this[UserTable.name]
        )
    }
}