package me.dio.creditrequestsystem.service.impl

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.dio.creditrequestsystem.entity.Address
import me.dio.creditrequestsystem.entity.Costumer
import me.dio.creditrequestsystem.exception.BusinessException
import me.dio.creditrequestsystem.repository.CostumerRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CostumerServiceTest {
    @MockK
    lateinit var costumerRepository: CostumerRepository

    @InjectMockKs
    lateinit var costumerService: CostumerService

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `should create costumer`() {
        //given
        val fakeCostumer: Costumer = buildCostumer()
        every { costumerRepository.save(any()) } returns fakeCostumer
        //when
        val actual: Costumer = costumerService.save(fakeCostumer)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCostumer)
        verify(exactly = 1) { costumerRepository.save(fakeCostumer) }
    }

    @Test
    fun `should find costumer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCostumer: Costumer = buildCostumer(id = fakeId)
        every { costumerRepository.findById(fakeId) } returns Optional.of(fakeCostumer)
        //when
        val actual: Costumer = costumerService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Costumer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCostumer)
        verify(exactly = 1) { costumerRepository.findById(fakeId) }
    }

    @Test
    fun `should not find costumer by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { costumerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { costumerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { costumerRepository.findById(fakeId) }
    }

    @Test
    fun `should delete costumer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCostumer: Costumer = buildCostumer(id = fakeId)
        every { costumerRepository.findById(fakeId) } returns Optional.of(fakeCostumer)
        every { costumerRepository.delete(fakeCostumer) } just runs
        //when
        costumerService.delete(fakeId)
        //then
        verify(exactly = 1) { costumerRepository.findById(fakeId) }
        verify(exactly = 1) { costumerRepository.delete(fakeCostumer) }
    }

    companion object {
        fun buildCostumer(
            firstName: String = "Cami",
            lastName: String = "Cavalcante",
            cpf: String = "28475934625",
            email: String = "camila@gmail.com",
            password: String = "12345",
            zipCode: String = "12345",
            street: String = "Rua da Cami",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
        ) = Costumer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street,
            ),
            income = income,
            id = id
        )
    }

}