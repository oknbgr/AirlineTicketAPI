package com.example.AirlineTicketAPI.service

import com.example.AirlineTicketAPI.model.User
import com.example.AirlineTicketAPI.repository.UserRepository
import org.springframework.stereotype.Service

// Business Layer: User Service
@Service
class UserService(
        private val userRepository: UserRepository
) {
    fun save(user: User): User = userRepository.save(user)

    fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    fun findById(id: Int): User = userRepository.findById(id).get()
}