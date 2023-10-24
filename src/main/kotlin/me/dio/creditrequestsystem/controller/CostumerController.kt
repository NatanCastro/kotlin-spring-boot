package me.dio.creditrequestsystem.controller

import jakarta.validation.Valid
import me.dio.creditrequestsystem.dto.request.CostumerDto
import me.dio.creditrequestsystem.dto.request.CostumerUpdateDto
import me.dio.creditrequestsystem.dto.response.CostumerView
import me.dio.creditrequestsystem.entity.Costumer
import me.dio.creditrequestsystem.service.impl.CostumerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/costumers")
class CostumerController(
    private val costumerService: CostumerService
) {
    @PostMapping
    fun createCostumer(@RequestBody @Valid costumerDto: CostumerDto): String {
        val costumer = costumerService.save(costumerDto.toEntity())

        return "Costumer ${costumer.email} created successfully"
    }

    @GetMapping("/{id}")
    fun getCostumers(@PathVariable id: Long): ResponseEntity<CostumerView> {
        return ResponseEntity.status(HttpStatus.OK).body(CostumerView(costumerService.findById(id)))
    }

    @DeleteMapping("/{id}")
    fun deleteCostumer(@PathVariable id: Long): ResponseEntity<Unit> {
        costumerService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    fun updateCostumer(
        @PathVariable id: Long,
        @RequestBody @Valid costumerUpdateDto: CostumerUpdateDto
    ): ResponseEntity<CostumerView> {
        val customer: Costumer = this.costumerService.findById(id)
        val cutomerToUpdate: Costumer = costumerUpdateDto.toEntity(customer)
        val customerUpdated: Costumer = this.costumerService.save(cutomerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CostumerView(customerUpdated))
    }
}
