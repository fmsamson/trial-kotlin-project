package com.exercise.cleanarch.ports.service

import com.exercise.cleanarch.ports.controller.UserRequestProvider
import com.exercise.cleanarch.ports.controller.UserResponseProvider

interface UserServiceProvider {
    fun buildAndSaveUser(
        id: Long,
        userRequestProvider: UserRequestProvider,
        userResponseProvider: UserResponseProvider
    ): UserResponseProvider

    fun findUserByIdAndBuild(id: Long, userResponseProvider: UserResponseProvider): UserResponseProvider
}