package com.exercise.cleanarch.core.service

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.ports.repository.UserRepositoryProvider
import com.exercise.cleanarch.ports.controller.UserRequestProvider
import com.exercise.cleanarch.ports.controller.UserResponseProvider
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {
    @InjectMocks
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepositoryProvider: UserRepositoryProvider

    @Mock
    private lateinit var userRequestProvider: UserRequestProvider

    @Mock
    private lateinit var userResponseProvider: UserResponseProvider

    @Test
    fun testCrud() {
        val testId = 123L
        val testName = "123 Name"

        val user = User()
        user.id = testId
        user.name = testName

        Mockito.`when`(userRepositoryProvider.save(any())).thenReturn(user)
        userService.saveUser(user)

        Mockito.`when`(userRepositoryProvider.findById(testId)).thenReturn(Optional.of(user))
        val userFromDb = userService.findUserById(testId)

        Assertions.assertThat(userFromDb.isPresent).isEqualTo(true)
        Assertions.assertThat(userFromDb.get().id).isEqualTo(testId)
        Assertions.assertThat(userFromDb.get().name).isEqualTo(testName)
    }

    @Test
    fun testBuildAndSaveUser() {
        val testId = 123L
        val testName = "123 Name"

        val user = User()
        user.id = testId
        user.name = testName

        Mockito.`when`(userRequestProvider.buildUser(testId)).thenReturn(user)
        Mockito.`when`(userRepositoryProvider.save(any())).thenReturn(user)

        userService.buildAndSaveUser(testId, userRequestProvider, userResponseProvider)
        verify(userRequestProvider, times(1)).buildUser(any())
        verify(userRepositoryProvider, times(1)).save(any())
    }

    @Test
    fun testFindUserByIdAndBuild() {
        val testId = 123L
        val testName = "123 Name"

        userService.findUserByIdAndBuild(testId, userResponseProvider)
        verify(userResponseProvider, times(0)).buildUserResponseProvider(any())
        verify(userRepositoryProvider, times(1)).findById(testId)

        val user = User()
        user.id = testId
        user.name = testName

        Mockito.`when`(userRepositoryProvider.findById(testId)).thenReturn(Optional.of(user))

        val userResponse = userService.findUserByIdAndBuild(testId, TestUserResponse()) as TestUserResponse
        verify(userRepositoryProvider, times(2)).findById(testId)
        Assertions.assertThat(userResponse.id).isEqualTo(testId)
        Assertions.assertThat(userResponse.name).isEqualTo(testName)
    }
}

private class TestUserResponse(val id: Long? = null, val name: String? = null) : UserResponseProvider {
    override fun buildUserResponseProvider(user: User): UserResponseProvider {
        return TestUserResponse(user.id, user.name)
    }
}