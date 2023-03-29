package com.example.AirlineTicketAPI.utils.mapper

import com.example.AirlineTicketAPI.dto.QueryTicketResponseDTO
import com.example.AirlineTicketAPI.model.Ticket
import org.springframework.stereotype.Component

@Component
class QueryResponseMapper: Mapper<QueryTicketResponseDTO, Ticket> {
    override fun toEntity(dto: QueryTicketResponseDTO): Ticket {
        val ticket = Ticket()
        ticket.id = dto.id
        ticket.date = dto.date
        ticket.price = dto.price
        return ticket
    }

    override fun toDTO(entity: Ticket): QueryTicketResponseDTO {
        val dto = QueryTicketResponseDTO()
        dto.id = entity.id
        dto.date = entity.date
        dto.price = entity.price
        return dto
    }
}