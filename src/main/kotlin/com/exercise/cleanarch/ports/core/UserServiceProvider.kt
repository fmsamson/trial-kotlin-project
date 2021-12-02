package com.exercise.cleanarch.ports.core

import com.exercise.cleanarch.ports.infra.UserInboundProvider
import com.exercise.cleanarch.ports.infra.UserOutboundProvider

interface UserServiceProvider {
    fun buildAndSaveUser(
        id: Long,
        userInboundProvider: UserInboundProvider,
        userOutboundProvider: UserOutboundProvider
    ): UserOutboundProvider

    fun findUserByIdAndBuild(id: Long, userOutboundProvider: UserOutboundProvider): UserOutboundProvider
}