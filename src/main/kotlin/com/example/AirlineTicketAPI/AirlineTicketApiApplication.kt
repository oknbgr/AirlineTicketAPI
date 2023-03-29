package com.example.AirlineTicketAPI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class AirlineTicketApiApplication

fun main(args: Array<String>) {
	runApplication<AirlineTicketApiApplication>(*args)
}
