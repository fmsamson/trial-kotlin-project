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

    internal fun saveUser(user: User): User {
        return userRepositoryProvider.save(user)
    }

    internal fun findUserById(id: Long): Optional<User> {
        return userRepositoryProvider.findById(id)
    }

    override fun buildAndSaveUser(
        id: Long,
        userRequestProvider: UserRequestProvider,
        userResponseProvider: UserResponseProvider
    ): UserResponseProvider {
        val user = saveUser(userRequestProvider.buildUser(id))
        return userResponseProvider.buildUserResponseProvider(user)
    }

    override fun findUserByIdAndBuild(id: Long, userResponseProvider: UserResponseProvider): UserResponseProvider {
        val user = findUserById(id)
        return if (user.isPresent)
            userResponseProvider.buildUserResponseProvider(user.get())
        else userResponseProvider
    }

}
