package com.github.tomplum.activity.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Enables CORS across all the API endpoints, allowing the UI to make fetch()
 * requests to the API. Without, Cross Origin errors are thrown in the browser.
 */
@EnableWebMvc
@Configuration
open class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }
}