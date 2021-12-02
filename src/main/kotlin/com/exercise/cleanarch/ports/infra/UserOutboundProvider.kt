package com.exercise.cleanarch.ports.infra

import com.exercise.cleanarch.core.entity.User

interface UserOutboundProvider {
    fun buildUserOutbound(user: User): UserOutboundProvider
}
