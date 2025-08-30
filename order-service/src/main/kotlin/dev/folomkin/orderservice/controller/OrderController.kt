package dev.folomkin.orderservice.controller

import dev.folomkin.orderservice.service.UserClientService
import dev.folomkin.shared.dto.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(private val userClientService: UserClientService) {
    @GetMapping("/user/{id}")
    fun getUserForOrder(@PathVariable id: Long): UserDto? {
        return userClientService.getUserById(id)
    }

    @GetMapping("/users")
    fun getAllUsers(): List<UserDto> {
        return userClientService.getAllUsers()
    }
}