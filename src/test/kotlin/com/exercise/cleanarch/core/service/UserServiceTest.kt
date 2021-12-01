package com.exercise.cleanarch.core.service

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.core.provider.UserRepositoryProvider
import com.exercise.cleanarch.core.provider.UserRequestProvider
import com.exercise.cleanarch.core.provider.UserResponseProvider
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
        userService.createUser(user)

        Mockito.`when`(userRepositoryProvider.findById(testId)).thenReturn(Optional.of(user))
        val userFromDb = userService.getUserById(testId)

        Assertions.assertThat(userFromDb.isPresent).isEqualTo(true)
        Assertions.assertThat(userFromDb.get().id).isEqualTo(testId)
        Assertions.assertThat(userFromDb.get().name).isEqualTo(testName)
    }

    @Test
    fun testConvertAndCreate() {
        val testId = 123L
        val testName = "123 Name"

        val user = User()
        user.id = testId
        user.name = testName

        Mockito.`when`(userRequestProvider.convert(testId)).thenReturn(user)

        userService.convertAndCreateUser(testId, userRequestProvider)
        verify(userRequestProvider, times(1)).convert(any())
        verify(userRepositoryProvider, times(1)).save(any())
    }

    @Test
    fun testGetUserByIdAndConvert() {
        val testId = 123L
        val testName = "123 Name"

        userService.getUserByIdAndConvert(testId, userResponseProvider)
        verify(userResponseProvider, times(0)).populate(any())
        verify(userRepositoryProvider, times(1)).findById(testId)

        val user = User()
        user.id = testId
        user.name = testName

        Mockito.`when`(userRepositoryProvider.findById(testId)).thenReturn(Optional.of(user))

        userService.getUserByIdAndConvert(testId, userResponseProvider)
        verify(userResponseProvider, times(1)).populate(any())
        verify(userRepositoryProvider, times(2)).findById(testId)
    }
}