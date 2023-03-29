package com.example.AirlineTicketAPI.controller

import com.example.AirlineTicketAPI.dto.QueryTicketRequestDTO
import com.example.AirlineTicketAPI.dto.QueryTicketResponseDTO
import com.example.AirlineTicketAPI.model.Ticket
import com.example.AirlineTicketAPI.service.TicketService
import com.example.AirlineTicketAPI.utils.mapper.QueryRequestMapper
import com.example.AirlineTicketAPI.utils.mapper.QueryResponseMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tickets/")
class TicketController(
        private val queryRequestMapper: QueryRequestMapper,
        private val queryResponseMapper: QueryResponseMapper,
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

    /*
    @GetMapping("/{date}/{from}/{to}/{seats}")
    fun getTicket(
            @PathVariable date: String,
            @PathVariable from: String,
            @PathVariable to: String,
            @PathVariable seats: Int
    ): Ticket = service.getTicket(date, from, to, seats)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTicket(@RequestBody ticket: Ticket): Ticket = service.addTicket(ticket)

    @GetMapping("getMyTickets")
    fun queryTickets(
            @RequestBody dto: QueryTicketRequestDTO
    ): Collection<QueryTicketResponseDTO>  {
        val tickets = service.queryTickets(queryRequestMapper.toEntity(dto))
        val dtos = mutableListOf<QueryTicketResponseDTO>()

        for (ticket in tickets) {
            dtos.add(queryResponseMapper.toDTO(ticket))
        }

        return dtos
    }
}