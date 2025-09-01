package dev.folomkin.userservice.service

import dev.folomkin.shared.dto.UserDto
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserProducer(private val kafkaTemplate: KafkaTemplate<String, UserDto>) {
    fun sendUser(userDto: UserDto) {
        kafkaTemplate.send(
            "user-topic",
            userDto
        )  // Отправка в топик 'user-topic'
        println("Sent ${userDto.name} to ${userDto.email}")
    }
}