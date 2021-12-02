package com.exercise.cleanarch.ports.infra

import com.exercise.cleanarch.core.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepositoryProvider : JpaRepository<User, Long>