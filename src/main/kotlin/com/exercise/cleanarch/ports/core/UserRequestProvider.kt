package com.exercise.cleanarch.ports.core

import com.exercise.cleanarch.core.entity.User

interface UserRequestProvider {
    fun buildUser(id: Long): User
}
