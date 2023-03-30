package com.example.AirlineTicketAPI.repository

import com.example.AirlineTicketAPI.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

//@Repository
interface TicketRepository: JpaRepository<Ticket, Int>
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

    fun findTicketById(
            id: Int
    ): Ticket

    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.seats = (t.seats-1) WHERE t.id = :id")
    fun updateTicketSeatsById(
            @Param("id") id: Int
    )
}