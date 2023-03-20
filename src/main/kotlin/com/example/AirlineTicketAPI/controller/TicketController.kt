package com.example.AirlineTicketAPI.controller

import com.example.AirlineTicketAPI.model.Ticket
import com.example.AirlineTicketAPI.service.TicketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tickets/")
class TicketController(
    private val service: TicketService
) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getTickets(): Collection<Ticket> = service.getTickets()

    @GetMapping("/{destination}")
    fun getTicket(@PathVariable destination: String): Ticket = service.getTicket(destination)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTicket(@RequestBody ticket: Ticket): Ticket = service.addTicket(ticket)
}