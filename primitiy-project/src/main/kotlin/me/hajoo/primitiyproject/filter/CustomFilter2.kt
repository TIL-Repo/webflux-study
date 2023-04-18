package me.hajoo.primitiyproject.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import me.hajoo.primitiyproject.event.EventNotify

class CustomFilter2(
    private val eventNotify: EventNotify
): Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("필터2 실행됨")

        eventNotify.add("새로운 데이터")
    }
}