package com.exercise.cleanarch.infra.controller

import com.exercise.cleanarch.core.provider.UserServiceProvider
import com.exercise.cleanarch.infra.controller.model.UserRequest
import com.exercise.cleanarch.infra.controller.model.UserResponse
import org.apache.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userServiceProvider: UserServiceProvider) {

    @PutMapping("/{id}")
    fun createUser(@PathVariable id: Long, @RequestBody userRequest: UserRequest) {
        userServiceProvider.convertAndCreateUser(id, userRequest)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) : ResponseEntity<UserResponse> {
        val userResponse = userServiceProvider.getUserByIdAndConvert(id, UserResponse()) as UserResponse
        return ResponseEntity.ok(userResponse)
    }
}