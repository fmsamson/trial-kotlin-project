package com.exercise.cleanarch

import com.exercise.cleanarch.infra.controller.UserController
import com.exercise.cleanarch.ports.core.UserServiceProvider
import com.exercise.cleanarch.ports.core.UserValidationServiceProvider
import com.exercise.cleanarch.ports.infra.UserRepositoryProvider
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MainAppIntegrationTest {

    @Autowired
    private lateinit var userController: UserController

    @Autowired
    private lateinit var userServiceProvider: UserServiceProvider

    @Autowired
    private lateinit var userValidationServiceProvider: UserValidationServiceProvider

    @Autowired
    private lateinit var userRepositoryProvider: UserRepositoryProvider

    @Test
    fun testContextLoads() {
        Assertions.assertThat(userController).isNotNull
        Assertions.assertThat(userServiceProvider).isNotNull
        Assertions.assertThat(userValidationServiceProvider).isNotNull
        Assertions.assertThat(userRepositoryProvider).isNotNull
    }
}