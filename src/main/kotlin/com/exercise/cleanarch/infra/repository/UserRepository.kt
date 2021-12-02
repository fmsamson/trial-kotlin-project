package com.exercise.cleanarch.infra.repository

import com.exercise.cleanarch.ports.repository.UserRepositoryProvider
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : UserRepositoryProvider