package com.exercise.cleanarch.infra.controller.model

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.core.provider.UserResponseProvider

data class UserResponse(val id: Long? = null, val name: String? = null): UserResponseProvider {
    override fun populate(user: User): UserResponseProvider {
        return UserResponse(user.id, user.name)
    }

}
