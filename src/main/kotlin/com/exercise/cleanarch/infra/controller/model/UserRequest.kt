package com.exercise.cleanarch.infra.controller.model

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.core.provider.UserRequestProvider

class UserRequest : UserRequestProvider {
    var name: String? = null

    override fun convert(id: Long): User {
        val user = User()
        user.id = id
        user.name = this.name
        return user
    }
}
