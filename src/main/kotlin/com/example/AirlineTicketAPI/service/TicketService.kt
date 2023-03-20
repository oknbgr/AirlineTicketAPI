package com.example.AirlineTicketAPI.service

import com.example.AirlineTicketAPI.datasource.TicketDataSource
import com.example.AirlineTicketAPI.model.Ticket
import org.springframework.stereotype.Service

// Ticket Service: Service Layer (Services, Business Logic)
@Service
class TicketService(
    private val dataSource: TicketDataSource
) {
    fun getTickets(): Collection<Ticket> = dataSource.retrieveTickets()
    fun getTicket(destination: String): Ticket = dataSource.retrieveTicket(destination)
}