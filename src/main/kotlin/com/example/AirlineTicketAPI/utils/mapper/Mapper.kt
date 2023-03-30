package com.example.AirlineTicketAPI.utils.mapper

// Mapper utilities for mapping between entities and DTOs
interface Mapper<D, E> {
    fun toEntity(dto: D): E
    fun toDTO(entity: E): D
}