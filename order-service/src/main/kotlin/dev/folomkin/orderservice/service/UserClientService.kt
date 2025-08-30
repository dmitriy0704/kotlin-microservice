package dev.folomkin.orderservice.service

import dev.folomkin.shared.dto.UserDto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class UserClientService(private val restTemplate: RestTemplate) {
    private val userServiceUrl = "http://localhost:8081/users"

    fun getUserById(id: Long): UserDto? {
        return restTemplate.getForObject("$userServiceUrl/$id", UserDto::class.java)
    }

    fun getAllUsers(): List<UserDto> {
        val response = restTemplate.getForEntity(userServiceUrl, Array<UserDto>::class.java)
        return response.body?.toList() ?: emptyList()
    }
}