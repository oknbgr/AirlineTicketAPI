package com.example.AirlineTicketAPI.dto

open class QueryTicketResponseDTO {
    var id: Int = 0 // = flight no
        get() = field
        set(value) {
            field = value
        }
    var date  = ""
        get() = field
        set(value) {
            field = value
        }

    var price: Double = 0.0
        get() = field
        set(value) {
            field = value
        }
}