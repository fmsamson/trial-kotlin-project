package com.exercise.cleanarch.infra.controller

import com.exercise.cleanarch.infra.controller.model.UserRequest
import com.exercise.cleanarch.infra.controller.model.UserResponse
import com.exercise.cleanarch.ports.core.UserServiceProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userServiceProvider: UserServiceProvider) {

    @PutMapping("/{id}")
    fun createUser(@PathVariable id: Long, @RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        val userResponse = userServiceProvider.buildAndSaveUser(id, userRequest, UserResponse()) as UserResponse
        return ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val userResponse = userServiceProvider.findUserByIdAndBuild(id, UserResponse()) as UserResponse
        return ResponseEntity.ok(userResponse)
    }

}