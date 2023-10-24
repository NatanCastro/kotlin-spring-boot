package me.dio.creditrequestsystem.repository

import me.dio.creditrequestsystem.entity.Costumer
import org.springframework.data.jpa.repository.JpaRepository

interface CostumerRepository: JpaRepository<Costumer, Long> {}