package dev.folomkin.orderservice.service

import dev.folomkin.shared.dto.UserDto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient

@Service
class UserClientService(private val webClient: WebClient) {
    private val userServiceUrl = "http://localhost:8081/users"

    fun getUserById(id: Long): UserDto? {
        return webClient.get()
            .uri("$userServiceUrl/$id")
            .retrieve()
            .bodyToMono(UserDto::class.java)
            .block() // Блокирующий вызов для простоты; в реальных проектах используйте реактивный подход
    }

    fun getAllUsers(): List<UserDto> {
        return webClient.get()
            .uri(userServiceUrl)
            .retrieve()
            .bodyToFlux(UserDto::class.java)
            .collectList()
            .block() ?: emptyList()
    }
}