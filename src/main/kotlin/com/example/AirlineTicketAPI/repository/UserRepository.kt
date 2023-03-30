package com.example.AirlineTicketAPI.repository

import com.example.AirlineTicketAPI.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}