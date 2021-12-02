package com.exercise.cleanarch.core.service

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.ports.core.UserServiceProvider
import com.exercise.cleanarch.ports.infra.UserInboundProvider
import com.exercise.cleanarch.ports.infra.UserOutboundProvider
import com.exercise.cleanarch.ports.infra.UserRepositoryProvider
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
        userInboundProvider: UserInboundProvider,
        userOutboundProvider: UserOutboundProvider
    ): UserOutboundProvider {
        val user = saveUser(userInboundProvider.buildUser(id))
        return userOutboundProvider.buildUserOutbound(user)
    }

    override fun findUserByIdAndBuild(id: Long, userOutboundProvider: UserOutboundProvider): UserOutboundProvider {
        val user = findUserById(id)
        return if (user.isPresent)
            userOutboundProvider.buildUserOutbound(user.get())
        else userOutboundProvider
    }

}
