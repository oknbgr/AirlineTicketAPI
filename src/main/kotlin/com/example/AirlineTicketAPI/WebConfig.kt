package com.example.AirlineTicketAPI

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

// for avoiding CORS problems at frontend and allowing credentials
@Configuration
@EnableWebMvc // apply it to every controller
class WebConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // all allowed origins will match this path
                .allowedOrigins("http://localhost:3000", "http://localhost:8080", "http://localhost:4200")
                .allowCredentials(true) // for letting frontend getting the cookie
    }
}