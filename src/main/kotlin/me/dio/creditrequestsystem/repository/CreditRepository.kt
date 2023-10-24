package me.dio.creditrequestsystem.repository

import org.springframework.data.jpa.repository.JpaRepository
import me.dio.creditrequestsystem.entity.Credit
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface CreditRepository: JpaRepository<Credit, Long> {
    fun findByCreditCode(code: UUID): Credit?

    @Query(value = "SELECT * FROM CREDIT WHERE COSTUMER_ID = ?1", nativeQuery = true)
    fun findAllByCostumerId(costumerId: Long): List<Credit>
}