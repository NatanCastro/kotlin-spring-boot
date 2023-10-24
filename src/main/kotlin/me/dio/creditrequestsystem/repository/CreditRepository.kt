package me.dio.creditrequestsystem.repository

import org.springframework.data.jpa.repository.JpaRepository
import me.dio.creditrequestsystem.entity.Credit

interface CreditRepository: JpaRepository<Credit, Long> {}