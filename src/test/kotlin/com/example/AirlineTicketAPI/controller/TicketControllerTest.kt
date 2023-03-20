package com.example.AirlineTicketAPI.controller

import com.example.AirlineTicketAPI.model.Ticket
import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class TicketControllerTest @Autowired constructor( // Spring Boot's way of dependency injection
    // Allows making requests to rest api without actually issuing any http request
    val mockMvc: MockMvc,
    // Serializing data when using http post
    val objectMapper: ObjectMapper
) {
    val baseUrl = "/api/tickets/"

    @Nested
    @DisplayName("GET api/tickets/")
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
    @DisplayName("GET api/ticket/{to}")
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

    @Nested
    @DisplayName("POST /api/tickets/")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewTicket {
        @Test
        fun `should add a new ticket`() {
            // given
            val newTicket = Ticket(null, null, "kayseri", "gaziantep", 200)

            // when
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                // for serializing to a json object
                content = objectMapper.writeValueAsString(newTicket)
            }

                // then
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.from") { value("kayseri") }
                    jsonPath("$.to") { value("gaziantep") }
                    jsonPath("$.passengerCount") { value("200") }
                }
        }

        @Test
        fun `should return BAD REQUEST if ticket with given destination already exists`() {
            // given
            val invalidTicket = Ticket(null, null, "kayseri", "gaziantep", 200)

            // when
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidTicket)
            }

                // then
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }
}