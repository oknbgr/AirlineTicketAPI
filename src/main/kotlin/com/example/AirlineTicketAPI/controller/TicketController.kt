package com.example.AirlineTicketAPI.controller

import com.example.AirlineTicketAPI.model.Ticket
import com.example.AirlineTicketAPI.service.TicketService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tickets")
class TicketController(
    private val service: TicketService
) {

    @GetMapping
    fun getTickets(): Collection<Ticket> = service.getTickets()
}