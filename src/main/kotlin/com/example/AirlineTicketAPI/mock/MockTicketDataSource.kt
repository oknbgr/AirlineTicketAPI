package com.example.AirlineTicketAPI.mock

import com.example.AirlineTicketAPI.datasource.TicketDataSource
import com.example.AirlineTicketAPI.model.Ticket
import org.springframework.stereotype.Repository

// Mock data source for testing methods before moving to SQL.
@Repository // Marks this class as a data source in spring boot
class MockTicketDataSource : TicketDataSource {
    val tickets = mutableListOf(
        Ticket(null, null, "izmir", "istanbul", 100),
        Ticket(null, null, "ankara", "antalya", 150),
        Ticket(null, null, "kars", "sakarya", 50)
    )

    // In Kotlin, it is possible to use "=" to return something if there is nothing else to do inside the method
    override fun retrieveTickets(): Collection<Ticket> = tickets
    override fun retrieveTicket(destination: String): Ticket = tickets.firstOrNull() { it.to == destination }
        ?: throw NoSuchElementException("Could not find flights for $destination") // if it is null

    override fun createTicket(ticket: Ticket): Ticket {
        if (tickets.any { it.from == ticket.from && it.to == ticket.to }) {
            throw IllegalArgumentException("This flight already exists")
        }
        tickets.add(ticket)
        return ticket
    }
}