package com.exercise.cleanarch.core.service

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.core.provider.UserRepositoryProvider
import com.exercise.cleanarch.core.provider.UserRequestProvider
import com.exercise.cleanarch.core.provider.UserResponseProvider
import com.exercise.cleanarch.core.provider.UserServiceProvider
import org.springframework.stereotype.Service
import java.util.*

@Service
open class UserService(private val userRepositoryProvider: UserRepositoryProvider) : UserServiceProvider {

    internal fun createUser(user: User) {
        userRepositoryProvider.save(user)
    }

    internal fun getUserById(id: Long): Optional<User> {
        return userRepositoryProvider.findById(id)
    }

    override fun convertAndCreateUser(id: Long, userRequestProvider: UserRequestProvider) {
        createUser(userRequestProvider.convert(id))
    }

    override fun getUserByIdAndConvert(id: Long, userResponseProvider: UserResponseProvider): UserResponseProvider {
        val user = getUserById(id)
        if (user.isPresent) userResponseProvider.populate(user.get())
        return userResponseProvider
    }

}
