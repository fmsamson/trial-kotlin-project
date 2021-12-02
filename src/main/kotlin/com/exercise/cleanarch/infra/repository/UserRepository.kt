package com.exercise.cleanarch.infra.repository

import com.exercise.cleanarch.ports.infra.UserRepositoryProvider
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : UserRepositoryProvider