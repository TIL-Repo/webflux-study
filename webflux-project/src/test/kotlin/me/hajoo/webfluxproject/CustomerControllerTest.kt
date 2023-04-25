package me.hajoo.webfluxproject

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@WebFluxTest
class CustomerControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var customerRepository: CustomerRepository

    @Test
    @DisplayName("한 건 찾기")
    fun 한건_찾기() {
        // given
        val expectedBody = Mono.just(
            Customer(
                firstName = "joohyun",
                lastName = "ha"
            )
        )

        Mockito.`when`(customerRepository.findById(1L))
            .thenReturn(expectedBody)

        // when
        webTestClient.get()
            .uri("/customer/{id}", 1L)
            .exchange()
            .expectBody()
            .jsonPath("$.firstName").isEqualTo("joohyun")
            .jsonPath("$.lastname").isEqualTo("ha")

        // then
    }
}