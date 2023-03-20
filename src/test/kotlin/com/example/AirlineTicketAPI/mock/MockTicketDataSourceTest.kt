package com.example.AirlineTicketAPI.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockTicketDataSourceTest {
    private val mockTicketDataSource = MockTicketDataSource()

    @Test
    fun `should provide a collection of tickets`() {
        // when
        val tickets = mockTicketDataSource.retrieveTickets()

        // then
        assertThat(tickets).isNotEmpty
    }

    @Test
    fun `should provide some mock data`() {
        // when
        val tickets = mockTicketDataSource.retrieveTickets()

        // then
        // "it" keyword indicates what is returned from the method, like lambda symbol
        assertThat(tickets).allMatch { it.to.isNotBlank() }
    }
}