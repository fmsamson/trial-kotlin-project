package com.exercise.cleanarch.core.provider

interface UserServiceProvider {
    fun convertAndCreateUser(id: Long, userRequestProvider: UserRequestProvider)
    fun getUserByIdAndConvert(id: Long, userResponseProvider: UserResponseProvider): UserResponseProvider
}