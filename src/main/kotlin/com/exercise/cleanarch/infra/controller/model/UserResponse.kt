package com.exercise.cleanarch.infra.controller.model

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.ports.controller.UserResponseProvider

data class UserResponse(val id: Long? = null, val name: String? = null): UserResponseProvider {
    override fun buildUserResponseProvider(user: User): UserResponseProvider {
        return UserResponse(user.id, user.name)
    }

}
