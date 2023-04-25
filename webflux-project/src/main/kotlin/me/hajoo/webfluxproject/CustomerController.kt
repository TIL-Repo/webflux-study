package me.hajoo.webfluxproject

import org.springframework.http.MediaType
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import java.time.Duration

@RestController
class CustomerController(
    private val customerRepository: CustomerRepository,
) {

    val sinks = Sinks.many().multicast().onBackpressureBuffer<Customer>()

    @GetMapping("/flux")
    fun flux(): Flux<Int> {
        return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log()
    }

    @GetMapping("/fluxstream", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun fluxstream(): Flux<Int> {
        return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log()
    }

    @GetMapping("/customer", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun findAll(): Flux<Customer> {
        return customerRepository.findAll().delayElements(Duration.ofSeconds(1)).log()
    }

    @GetMapping("/customer/{id}")
    fun findById(@PathVariable id: Long): Mono<Customer> {
        return customerRepository.findById(id).log()
    }

    @GetMapping("/customer/sse")
    fun findAllSSE(): Flux<ServerSentEvent<Customer>> {
        return sinks.asFlux().map { ServerSentEvent.builder(it).build() }
    }

    @PostMapping("/customer")
    fun save(): Mono<Customer> {
        return customerRepository.save(Customer(
            firstName = "Joohyun",
            lastName = "Ha"
        )).doOnNext {
            sinks.tryEmitNext(it)
        }.log()
    }
}