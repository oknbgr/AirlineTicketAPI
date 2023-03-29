package com.example.AirlineTicketAPI.utils.mapper

import com.example.AirlineTicketAPI.dto.QueryTicketRequestDTO
import com.example.AirlineTicketAPI.model.Ticket
import org.springframework.stereotype.Component

@Component
class QueryRequestMapper: Mapper<QueryTicketRequestDTO, Ticket> {
    override fun toEntity(dto: QueryTicketRequestDTO): Ticket {
        val ticket = Ticket()
        ticket.date = dto.date
        ticket.from = dto.from
        ticket.to = dto.to
        ticket.seats = dto.seats
        return ticket
    }

    override fun toDTO(entity: Ticket): QueryTicketRequestDTO {
        val dto = QueryTicketRequestDTO()
        dto.date = entity.date
        dto.from = entity.from
        dto.to = entity.to
        dto.seats = entity.seats
        return dto
    }
}