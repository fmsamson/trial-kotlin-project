package com.exercise.cleanarch.ports.core

import com.exercise.cleanarch.core.entity.User

interface UserOutboundProvider {
    fun buildUserOutbound(user: User): UserOutboundProvider
}
