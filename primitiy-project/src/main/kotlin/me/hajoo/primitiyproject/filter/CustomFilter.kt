package me.hajoo.primitiyproject.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletResponse
import me.hajoo.primitiyproject.event.EventNotify

class CustomFilter(
    private val eventNotify: EventNotify
): Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        println("필터 실행됨")

        val servletResponse = response as HttpServletResponse
//        servletResponse.contentType = "text/plain;charset=utf-8"
        servletResponse.contentType = "text/event-stream;charset=utf-8"

        // Reactive Streams
        val out = servletResponse.writer
        for (i in 0..5) {
            out.println("응답 $i")
            out.flush()
            Thread.sleep(1000)
        }

        // SSE Emitter
        while (true) {
            if (eventNotify.isChange) {
                println(eventNotify.events)
                out.println("응답 : ${eventNotify.events[eventNotify.events.size - 1]}")
                out.flush()
                eventNotify.isChange = false
            }
            Thread.sleep(1000)
        }
    }
}