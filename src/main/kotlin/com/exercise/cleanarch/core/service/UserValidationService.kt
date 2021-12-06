package com.exercise.cleanarch.core.service

import com.exercise.cleanarch.ports.core.UserValidationServiceProvider
import com.exercise.cleanarch.ports.infra.UserRepositoryProvider
import org.springframework.stereotype.Service

@Service
class UserValidationService (private val userRepositoryProvider: UserRepositoryProvider) :
    UserValidationServiceProvider {
    override fun isUserExist(id: Long): Boolean {
        return userRepositoryProvider.existsById(id)
    }
}
