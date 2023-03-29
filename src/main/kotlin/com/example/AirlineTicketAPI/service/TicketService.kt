package com.example.AirlineTicketAPI.service

import com.example.AirlineTicketAPI.model.Ticket
import com.example.AirlineTicketAPI.repository.TicketRepository
import com.example.AirlineTicketAPI.utils.mapper.QueryResponseMapper
import org.springframework.stereotype.Service

// Ticket Service: Service Layer (Services, Business Logic)
@Service
class TicketService(
        private val mapper: QueryResponseMapper,
        private val dataSource: TicketRepository
) {
    fun getTickets(): Collection<Ticket> = dataSource.getAllTicketsBy()
    fun getTicket(
            d: String,
            f: String,
            t: String,
            s: Int
    ): Ticket = dataSource.findTicketByDateAndFromAndToAndSeats(d, f, t, s)
    fun addTicket(ticket: Ticket): Ticket = dataSource.save(ticket)
    fun queryTickets(
            t: Ticket
    ): Collection<Ticket> = dataSource.findTicketsByDateAndFromAndToAndSeats(
            t.date,
            t.from,
            t.to,
            t.seats
    )

    //TODO: fun buyTicket()
}