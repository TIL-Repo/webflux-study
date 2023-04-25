package me.hajoo.webfluxproject

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface CustomerRepository: ReactiveCrudRepository<Customer, Long> {

    @Query("SELECT * FROM customer WHERE last_name = $1")
    fun findByLastName(lastName: String): Flux<Customer>
}