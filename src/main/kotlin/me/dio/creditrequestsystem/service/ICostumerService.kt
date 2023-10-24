package me.dio.creditrequestsystem.service

import me.dio.creditrequestsystem.entity.Costumer

interface ICostumerService {
    fun save(costumer: Costumer): Costumer
    fun findById(id: Long): Costumer
    fun delete(id: Long): Unit
}