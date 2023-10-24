package me.dio.creditrequestsystem.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
import me.dio.creditrequestsystem.enummeration.Status

@Getter
@Setter
@Entity 
@Table
data class Credit (
    @Column(nullable = false, unique = true) var creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate,
    @Column(nullable = false) val numberOfInstallments: Int = 0,
    @Enumerated val status: Status = Status.IN_PROGRESS,
    @ManyToOne var customer: Costumer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null) {
}
