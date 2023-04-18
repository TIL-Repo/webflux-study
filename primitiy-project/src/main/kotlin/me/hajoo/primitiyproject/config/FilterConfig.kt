package me.hajoo.primitiyproject.config

import me.hajoo.primitiyproject.event.EventNotify
import me.hajoo.primitiyproject.filter.CustomFilter
import me.hajoo.primitiyproject.filter.CustomFilter2
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig(
    private val eventNotify: EventNotify
) {

    @Bean
    fun customFilter(): FilterRegistrationBean<CustomFilter> {
        println("필터 등록됨")
        val bean = FilterRegistrationBean(CustomFilter(eventNotify))
        bean.addUrlPatterns("/sse")
        return bean
    }

    @Bean
    fun customFilter2(): FilterRegistrationBean<CustomFilter2> {
        println("필터 등록됨2")
        val bean = FilterRegistrationBean(CustomFilter2(eventNotify))
        bean.addUrlPatterns("/add")
        return bean
    }
}