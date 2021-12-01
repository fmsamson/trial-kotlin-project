package com.exercise.cleanarch.core.provider

interface UserServiceProvider {
    fun buildAndSaveUser(
        id: Long,
        userRequestProvider: UserRequestProvider,
        userResponseProvider: UserResponseProvider
    ): UserResponseProvider

    fun findUserByIdAndBuild(id: Long, userResponseProvider: UserResponseProvider): UserResponseProvider
}