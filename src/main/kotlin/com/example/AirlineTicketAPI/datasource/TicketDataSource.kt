package com.example.AirlineTicketAPI.datasource

import com.example.AirlineTicketAPI.model.Ticket

// Ticket Data Source (Data Retrieval, Storage)
interface TicketDataSource {
    fun retrieveTickets(): Collection<Ticket>
}