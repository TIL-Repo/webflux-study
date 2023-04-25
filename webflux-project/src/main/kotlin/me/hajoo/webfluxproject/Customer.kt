package me.hajoo.webfluxproject

import jakarta.persistence.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Customer(
    @Id
    val id: Long? = null,
    var firstName: String,
    var lastName: String
) {
}