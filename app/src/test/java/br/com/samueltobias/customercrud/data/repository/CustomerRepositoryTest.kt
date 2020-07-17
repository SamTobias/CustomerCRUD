package br.com.samueltobias.customercrud.data.repository

import br.com.samueltobias.customercrud.data.database.dao.CustomerDao
import br.com.samueltobias.customercrud.domain.model.Customer
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CustomerRepositoryTest {
    @Test
    fun fetch() = runBlocking {
        val customers: MutableList<Customer> = ArrayList()
        customers.add(Customer(1, "PDV A", "99999-9999"))
        customers.add(Customer(2, "PDV B", "99999-9999"))

        val customerDaoMock = mockk<CustomerDao>()
        val customerRepository = spyk(CustomerRepository(customerDaoMock))

        coEvery { customerDaoMock.getAll() } returns customers

        val result = customerRepository.getCustomers()

        Assert.assertEquals(customers.size, result.size)
    }
}
