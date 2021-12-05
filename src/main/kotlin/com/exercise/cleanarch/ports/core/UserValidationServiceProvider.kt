package com.exercise.cleanarch.ports.core

interface UserValidationServiceProvider {
    fun isUserExist(id: Long): Boolean
}