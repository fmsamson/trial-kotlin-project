package com.exercise.cleanarch.ports.core

import com.exercise.cleanarch.core.entity.User

interface UserResponseProvider {
    fun buildUserResponseProvider(user: User): UserResponseProvider
}
