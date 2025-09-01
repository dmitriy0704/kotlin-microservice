package dev.folomkin.orderservice.service

import dev.folomkin.shared.dto.UserDto
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
class UserConsumer {

    @KafkaListener(topics = ["users-topic"], groupId = "order-service-group")
    fun consume(user: UserDto) {
        println("Received user: $user")
    }
}