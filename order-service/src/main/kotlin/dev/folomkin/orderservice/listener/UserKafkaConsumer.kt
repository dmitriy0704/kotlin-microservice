package dev.folomkin.orderservice.listener


import dev.folomkin.shared.dto.UserDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class UserKafkaConsumer {

    private val logger = LoggerFactory.getLogger(UserKafkaConsumer::class.java)

    @KafkaListener(topics = ["user-topic"], groupId = "order-group")
    fun consumeUser(userDto: UserDto) {
        logger.info("Получен UserDto: {}", userDto)
        // Обработка полученного объекта: сохранение в базу, логика бизнеса и т.д.
    }
}