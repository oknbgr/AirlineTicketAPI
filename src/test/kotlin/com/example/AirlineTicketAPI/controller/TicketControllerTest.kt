package com.example.AirlineTicketAPI.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class TicketControllerTest {
    // Allows making requests to rest api without actually issuing any http request
    @Autowired // Spring Boot's way of dependency injection
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return all tickets`() {
        // when
        mockMvc.get("/api/tickets")
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