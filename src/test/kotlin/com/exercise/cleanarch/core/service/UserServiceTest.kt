package com.exercise.cleanarch.core.service

import com.exercise.cleanarch.core.entity.User
import com.exercise.cleanarch.ports.infra.UserInboundProvider
import com.exercise.cleanarch.ports.infra.UserOutboundProvider
import com.exercise.cleanarch.ports.infra.UserRepositoryProvider
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
    private lateinit var userInboundProvider: UserInboundProvider

    @Mock
    private lateinit var userOutboundProvider: UserOutboundProvider

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

        Mockito.`when`(userInboundProvider.buildUser(testId)).thenReturn(user)
        Mockito.`when`(userRepositoryProvider.save(any())).thenReturn(user)

        userService.buildAndSaveUser(testId, userInboundProvider, userOutboundProvider)
        verify(userInboundProvider, times(1)).buildUser(any())
        verify(userRepositoryProvider, times(1)).save(any())
    }

    @Test
    fun testFindUserByIdAndBuild() {
        val testId = 123L
        val testName = "123 Name"

        userService.findUserByIdAndBuild(testId, userOutboundProvider)
        verify(userOutboundProvider, times(0)).buildUserOutbound(any())
        verify(userRepositoryProvider, times(1)).findById(testId)

        val user = User()
        user.id = testId
        user.name = testName

        Mockito.`when`(userRepositoryProvider.findById(testId)).thenReturn(Optional.of(user))

        val userResponse = userService.findUserByIdAndBuild(testId, TestUserOutbound()) as TestUserOutbound
        verify(userRepositoryProvider, times(2)).findById(testId)
        Assertions.assertThat(userResponse.id).isEqualTo(testId)
        Assertions.assertThat(userResponse.name).isEqualTo(testName)
    }
}

private class TestUserOutbound(val id: Long? = null, val name: String? = null) : UserOutboundProvider {
    override fun buildUserOutbound(user: User): UserOutboundProvider {
        return TestUserOutbound(user.id, user.name)
    }
}