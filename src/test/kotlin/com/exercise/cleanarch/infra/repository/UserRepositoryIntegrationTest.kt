package com.exercise.cleanarch.infra.repository

import com.exercise.cleanarch.core.entity.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest(classes = [UserRepository::class])
@EnableAutoConfiguration
@EntityScan("com.exercise.cleanarch.core.entity")
class UserRepositoryIntegrationTest {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun testCrud() {
        val testId = 123L
        val testName = "123 Name"

        val user = User()
        user.id = testId
        user.name = testName

        userRepository.save(user)
        val userFromDb: Optional<User> = userRepository.findById(testId)

        Assertions.assertThat(userFromDb.isPresent).isEqualTo(true)
        Assertions.assertThat(userFromDb.get().id).isEqualTo(testId)
        Assertions.assertThat(userFromDb.get().name).isEqualTo(testName)
    }
}