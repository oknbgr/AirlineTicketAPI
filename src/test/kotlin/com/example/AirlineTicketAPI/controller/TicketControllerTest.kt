package com.example.AirlineTicketAPI.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class TicketControllerTest {
    // Allows making requests to rest api without actually issuing any http request
    @Autowired // Spring Boot's way of dependency injection
    lateinit var mockMvc: MockMvc
    val baseUrl = "/api/tickets/"

    @Nested
    @DisplayName("getTickets()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTickets {
        @Test
        fun `should return all tickets`() {
            // when
            mockMvc.get(baseUrl)
                .andDo { print() }

                // then
                .andExpect {
                    status { isOk() }
                    jsonPath("$[0].to") // $[0] = go to ticket, .to = go to "to"
                    {
                        value("istanbul") // if it goes to istanbul
                    }
                }
        }
    }

    @Nested
    @DisplayName("getTicket()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTicket {
        @Test
        fun `should return ticket with given where its going to`() {
            // given
            val goesTo = "antalya"

            // when
            mockMvc.get(baseUrl + goesTo)

                // then
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.passengerCount") {
                        value("150")
                    }
                }
        }

        @Test
        fun `should return NOT FOUND if destination does not exist`() {
            // given
            val goesTo = "bursa"

            // when
            mockMvc.get(baseUrl + goesTo)

                // then
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }
}