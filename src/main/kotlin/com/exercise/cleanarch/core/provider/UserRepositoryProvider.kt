package com.exercise.cleanarch.core.provider

import com.exercise.cleanarch.core.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepositoryProvider : JpaRepository<User, Long>