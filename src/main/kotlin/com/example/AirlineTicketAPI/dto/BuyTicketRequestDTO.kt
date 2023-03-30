package com.example.AirlineTicketAPI.dto

class BuyTicketRequestDTO {
    var id: Int = 0 // flight id is added for buying the exact ticket
        get() = field
        set(value) {
            field = value
        }
    var date = ""
        get() = field
        set(value) {
            field = value
        }

    var from = ""
        get() = field
        set(value) {
            field = value
        }

    var to = ""
        get() = field
        set(value) {
            field = value
        }

    var passenger = ""
        get() = field
        set(value) {
            field = value
        }
}