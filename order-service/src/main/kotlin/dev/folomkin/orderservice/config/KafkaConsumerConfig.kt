package dev.folomkin.orderservice.config

import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConsumerConfig {


    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        val configProps = mapOf(
            "bootstrap.servers" to "localhost:9092",
            "group.id" to "order-group",
            "auto.offset.reset" to "earliest"
//            "key.deserializer" to StringDeserializer::class.java,
//            "value.deserializer" to ErrorHandlingDeserializer::class.java,
//            "spring.json.trusted.packages" to "*"
        )
        val deserializer = JsonDeserializer(Any::class.java)
        deserializer.addTrustedPackages("*")
        return DefaultKafkaConsumerFactory(configProps, StringDeserializer(), ErrorHandlingDeserializer(deserializer))
    }

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, Any>): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory
        return factory
    }
}