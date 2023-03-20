package com.example.AirlineTicketAPI.service

import com.example.AirlineTicketAPI.datasource.TicketDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class TicketServiceTest {
    private val dataSource: TicketDataSource = mockk(relaxed = true)
    private val ticketService = TicketService(dataSource)

    @Test
    fun `should call its data source to retrieve tickets`() {
        // when
        ticketService.getTickets()

        // then
        verify(exactly = 1) { dataSource.retrieveTickets() }
    }
}