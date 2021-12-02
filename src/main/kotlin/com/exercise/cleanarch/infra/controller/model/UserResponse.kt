package com.exercise.cleanarch.infra.controller.model

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.ports.core.UserOutboundProvider

data class UserResponse(val id: Long? = null, val name: String? = null) : UserOutboundProvider {
    override fun buildUserOutbound(user: User): UserOutboundProvider {
        return UserResponse(user.id, user.name)
    }
}
