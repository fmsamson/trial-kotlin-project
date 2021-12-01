package com.exercise.cleanarch.infra.controller

import com.exercise.cleanarch.core.provider.UserServiceProvider
import com.exercise.cleanarch.infra.controller.model.UserResponse
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootTest(classes = [UserController::class])
@AutoConfigureMockMvc
@EnableWebMvc
class UserControllerIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userServiceProvider: UserServiceProvider

    @Test
    fun testCreateUser() {
        mockMvc.perform(
            MockMvcRequestBuilders.put("/users/123")
                .content("{\"name\":\"user 123\"}")
                .contentType("application/json")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)

        Mockito.verify(userServiceProvider, Mockito.times(1)).buildAndSaveUser(any(), any())
    }

    @Test
    fun testGetUser() {
        val testId = 123L
        val testName = "user 123"
        val testUserResponse = UserResponse(testId, testName)
        Mockito.`when`(userServiceProvider.findUserByIdAndBuild(testId, UserResponse())).thenReturn(testUserResponse)

         val userResponseEntity = mockMvc.perform(MockMvcRequestBuilders.get("/users/${testId}"))
             .andExpect(MockMvcResultMatchers.status().isOk)
             .andReturn()

        Assertions.assertThat(userResponseEntity.response.contentAsString).isEqualTo("{\"id\":${testId},\"name\":\"${testName}\"}")
    }
}