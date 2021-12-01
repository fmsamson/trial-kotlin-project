package com.exercise.cleanarch.core.provider

import com.exercise.cleanarch.core.entity.User

interface UserRequestProvider {
    fun buildUser(id: Long): User
}
