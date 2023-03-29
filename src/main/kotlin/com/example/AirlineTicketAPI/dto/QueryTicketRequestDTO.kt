package com.example.AirlineTicketAPI.dto

class QueryTicketRequestDTO {
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

    var seats: Int = 0
        get() = field
        set(value) {
            field = value
        }
}