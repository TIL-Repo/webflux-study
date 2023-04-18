package me.hajoo.primitiyproject.event

import org.springframework.stereotype.Component

@Component
class EventNotify {

    val events: MutableList<String> = mutableListOf();

    var isChange: Boolean = false

    fun add(data: String) {
        events.add(data)
        isChange = true
    }
}