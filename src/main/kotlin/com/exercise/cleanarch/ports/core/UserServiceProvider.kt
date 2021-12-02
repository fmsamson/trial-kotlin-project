package com.exercise.cleanarch.ports.core

interface UserServiceProvider {
    fun buildAndSaveUser(
        id: Long,
        userInboundProvider: UserInboundProvider,
        userOutboundProvider: UserOutboundProvider
    ): UserOutboundProvider

    fun findUserByIdAndBuild(id: Long, userOutboundProvider: UserOutboundProvider): UserOutboundProvider
}