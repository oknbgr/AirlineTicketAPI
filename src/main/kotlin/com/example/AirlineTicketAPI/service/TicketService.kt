package com.example.AirlineTicketAPI.service

import com.example.AirlineTicketAPI.dto.ticket.QueryTicketResponseDTO
import com.example.AirlineTicketAPI.model.Ticket
import com.example.AirlineTicketAPI.repository.TicketRepository
import com.example.AirlineTicketAPI.utils.mapper.QueryResponseMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
    fun queryTickets(t: Ticket): Collection<Ticket> = dataSource.findTicketsByDateAndFromAndToAndSeats(
            t.date,
            t.from,
            t.to,
            t.seats
    )

    fun getPagination(
            pageNum: Int,
            pageSize: Int,
            sortProperty: String,
            list: MutableList<QueryTicketResponseDTO>
    ): Page<QueryTicketResponseDTO> = PageImpl<QueryTicketResponseDTO> (
                list.subList(
                        (pageNum-1)*pageSize,
                        (pageNum*pageSize)
                ),
                PageRequest.of(
                        pageNum,
                        pageSize,
                        Sort.Direction.ASC,
                        sortProperty
                ),
                list.size.toLong()
    )

    fun buyTicket(t: Ticket) = dataSource.updateTicketSeatsById(
            dataSource.findTicketById(t.id).id
    )
}