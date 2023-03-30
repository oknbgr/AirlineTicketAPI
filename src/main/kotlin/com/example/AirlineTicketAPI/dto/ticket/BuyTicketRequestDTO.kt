package com.example.AirlineTicketAPI.dto.ticket

class BuyTicketRequestDTO {
    var id: Int = 0 // flight id is added for buying the exact ticket
    var date = ""
    var from = ""
    var to = ""
    var passenger = ""
}