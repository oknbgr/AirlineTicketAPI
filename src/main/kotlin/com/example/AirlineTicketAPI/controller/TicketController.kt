package com.example.AirlineTicketAPI.controller

import com.example.AirlineTicketAPI.dto.ticket.BuyTicketRequestDTO
import com.example.AirlineTicketAPI.dto.Message
import com.example.AirlineTicketAPI.dto.ticket.QueryTicketRequestDTO
import com.example.AirlineTicketAPI.dto.ticket.QueryTicketResponseDTO
import com.example.AirlineTicketAPI.model.Ticket
import com.example.AirlineTicketAPI.service.TicketService
import com.example.AirlineTicketAPI.utils.mapper.BuyRequestMapper
import com.example.AirlineTicketAPI.utils.mapper.QueryRequestMapper
import com.example.AirlineTicketAPI.utils.mapper.QueryResponseMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tickets/")
class TicketController(
        private val queryRequestMapper: QueryRequestMapper,
        private val queryResponseMapper: QueryResponseMapper,
        private val buyRequestMapper: BuyRequestMapper,
        private val service: TicketService
) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getTickets(): Collection<Ticket> = service.getTickets()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTicket(@RequestBody ticket: Ticket): Ticket = service.addTicket(ticket)

    @GetMapping("getMyTickets")
    fun queryTickets(
            @RequestBody dto: QueryTicketRequestDTO,
           // @RequestParam("page", defaultValue = "1") page: Int
    ): Any  { // originally returns Page<QueryTicketRequestDTO> but changed to Any for exception handling
        val tickets = service.queryTickets(queryRequestMapper.toEntity(dto))
        val dtos = mutableListOf<QueryTicketResponseDTO>()

        for (ticket in tickets) {
            dtos.add(queryResponseMapper.toDTO(ticket))
        }

        try {
            return service.getPagination(1, 2, "price", dtos)
        } catch (e: IndexOutOfBoundsException) {
            return Message("Requested pagination is out of bounds!")
        }
    }

    @PutMapping("buy")
    fun buyTicket(
            @RequestBody dto: BuyTicketRequestDTO
    ): Any {
        return service.buyTicket(buyRequestMapper.toEntity(dto))
    }
}