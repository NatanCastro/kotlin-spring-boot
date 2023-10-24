package me.dio.creditrequestsystem.service

import me.dio.creditrequestsystem.entity.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCostumer(costumerId: Long): List<Credit>
    fun findByCreditCode(costumerId: Long, creditCode: UUID): Credit
}