package com.exercise.cleanarch.core.provider

import com.exercise.cleanarch.core.entity.User

interface UserResponseProvider {
    fun populate(user: User): UserResponseProvider
}
