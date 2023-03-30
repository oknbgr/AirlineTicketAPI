package com.example.AirlineTicketAPI.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

// Ticket Model: Data Layer (Models, Serialization)
@Entity
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var date: String =""

    @Column
    var from: String = ""

    @Column
    var to: String = ""

    @Column
    var seats: Int = 0

    @Column
    var price: Double = 0.0
}