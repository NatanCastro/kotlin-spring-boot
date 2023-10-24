package me.dio.creditrequestsystem.service.impl

import me.dio.creditrequestsystem.repository.CostumerRepository
import me.dio.creditrequestsystem.service.ICostumerService
import me.dio.creditrequestsystem.entity.Costumer
import org.springframework.stereotype.Service

@Service
class CostumerService(
    private val costumerRepo: CostumerRepository
): ICostumerService {

    override fun save(costumer: Costumer): Costumer {
        return this.costumerRepo.save(costumer);
    }

    override fun findById(id: Long): Costumer {
        return this.costumerRepo.findById(id).orElseThrow {
            throw RuntimeException("Id: $id not found")
        }
    }
    

    override fun delete(id: Long): Unit {
        val costumer = this.findById(id)
        costumerRepo.delete(costumer)
    }
}