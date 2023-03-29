package com.example.AirlineTicketAPI.utils.mapper

interface Mapper<D, E> {
    fun toEntity(dto: D): E
    fun toDTO(entity: E): D
}