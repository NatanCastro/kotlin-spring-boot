package me.dio.creditrequestsystem.service.impl

import me.dio.creditrequestsystem.repository.CreditRepository
import me.dio.creditrequestsystem.service.ICreditService
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.service.ICostumerService
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class CreditService(
    private val creditRepo: CreditRepository,
    private val costumerService: ICostumerService
): ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            this.customer = costumerService.findById(customer?.id!!)
        }
        return this.creditRepo.save(credit)
    }

    override fun findAllByCostumer(costumerId: Long): List<Credit> {
        TODO("Not yet implemented")
    }

    @Throws(Exception::class)
    override fun findByCreditCode(costumerId: Long, creditCode: UUID): Credit {
        val credit = this.creditRepo.findByCreditCode(creditCode) ?: throw Exception("Credit not found")
        if (credit.customer?.id != costumerId) {
            throw Exception("Credit not found")
        }
        return credit
    }
}