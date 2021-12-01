package com.exercise.cleanarch.core.provider

interface UserServiceProvider {
    fun buildAndSaveUser(id: Long, userRequestProvider: UserRequestProvider)
    fun findUserByIdAndBuild(id: Long, userResponseProvider: UserResponseProvider): UserResponseProvider
}