package com.exercise.cleanarch.infra.aspect

import com.exercise.cleanarch.ports.core.UserValidationServiceProvider
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class ValidationAspect {

    private lateinit var userValidationServiceProvider: UserValidationServiceProvider

    fun setUserValidationProvider(userValidationServiceProvider: UserValidationServiceProvider) {
        this.userValidationServiceProvider = userValidationServiceProvider
    }

    @Before("@annotation(ValidateAccess) && args(id,..)")
    fun isUserExist(id: Long) {
        val isUserExist = userValidationServiceProvider.isUserExist(id)
        if (!isUserExist) {
            throw RuntimeException("Cannot Access")
        }
    }
}