package com.exercise.cleanarch.infra.controller.model

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.ports.core.UserRequestProvider

data class UserRequest(val name: String? = null) : UserRequestProvider {
    override fun buildUser(id: Long): User {
        val user = User()
        user.id = id
        user.name = this.name
        return user
    }
}
