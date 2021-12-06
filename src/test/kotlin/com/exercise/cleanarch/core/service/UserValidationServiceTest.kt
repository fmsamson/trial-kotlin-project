package com.exercise.cleanarch.core.service

import com.exercise.cleanarch.ports.infra.UserRepositoryProvider
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserValidationServiceTest {

    @InjectMocks
    private lateinit var userValidationService: UserValidationService

    @Mock
    private lateinit var userRepositoryProvider: UserRepositoryProvider

    @Test
    fun testIsUserExist() {
        val testId = 123L

        Mockito.`when`(userRepositoryProvider.existsById(testId)).thenReturn(false)
        var isUserExist = userValidationService.isUserExist(testId)
        Assertions.assertThat(isUserExist).isFalse

        Mockito.`when`(userRepositoryProvider.existsById(testId)).thenReturn(true)
        isUserExist = userValidationService.isUserExist(testId)
        Assertions.assertThat(isUserExist).isTrue
    }
}
