package me.hajoo.webfluxproject

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class DBConfig {

    @Bean
    fun demo(repository: CustomerRepository): CommandLineRunner {
        return CommandLineRunner {
            repository.saveAll(
                listOf(
                    Customer(firstName = "Jack", lastName = "Bauer"),
                    Customer(firstName = "Kim", lastName = "O'Brian"),
                    Customer(firstName = "David", lastName = "Palmer"),
                    Customer(firstName = "Michelle", lastName = "Dessler"),
                )
            ).blockLast(Duration.ofSeconds(10))
        }
    }
}