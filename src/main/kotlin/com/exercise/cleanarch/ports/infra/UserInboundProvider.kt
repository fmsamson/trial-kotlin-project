package com.exercise.cleanarch.ports.infra

import com.exercise.cleanarch.core.entity.User

interface UserInboundProvider {
    fun buildUser(id: Long): User
}
