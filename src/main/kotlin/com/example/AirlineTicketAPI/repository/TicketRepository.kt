package com.example.AirlineTicketAPI.repository

import com.example.AirlineTicketAPI.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository

//@Repository
interface TicketRepository: JpaRepository<Ticket, Int> //CrudRepository<Ticket, Int>
{
    fun getAllTicketsBy(): Collection<Ticket>
    fun findTicketByDateAndFromAndToAndSeats(
            date: String,
            from: String,
            to: String,
            seats: Int
    ): Ticket

    fun findTicketsByDateAndFromAndToAndSeats(
            date: String,
            from: String,
            to: String,
            seats: Int
    ): Collection<Ticket>


    //fun postTicket(ticket: Ticket): Ticket
}