package com.example.AirlineTicketAPI.utils.mapper

import com.example.AirlineTicketAPI.dto.BuyTicketRequestDTO
import com.example.AirlineTicketAPI.model.Ticket

class BuyRequestMapper: Mapper<BuyTicketRequestDTO, Ticket> {
    override fun toEntity(dto: BuyTicketRequestDTO): Ticket {
        val ticket = Ticket()
        ticket.date = dto.date
        ticket.from = dto.from
        ticket.to = dto.to
        return ticket
    }

    override fun toDTO(entity: Ticket): BuyTicketRequestDTO {
        val dto = BuyTicketRequestDTO()
        dto.date = entity.date
        dto.from = entity.date
        dto.to = entity.to
        return dto
    }
}