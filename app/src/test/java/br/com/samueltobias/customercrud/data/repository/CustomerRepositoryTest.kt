package br.com.samueltobias.customercrud.data.repository

import br.com.samueltobias.customercrud.data.database.dao.CustomerDao
import br.com.samueltobias.customercrud.domain.model.Customer
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.util.*

class CustomerRepositoryTest {
    @Test
    fun shouldGetListOfCustomers() = runBlocking {
        val customers: MutableList<Customer> = ArrayList()
        customers.add(Customer(1, "PDV A", "99999-9999"))
        customers.add(Customer(2, "PDV B", "99999-9999"))

        val customerDaoMock = mockk<CustomerDao>()
        val customerRepository = CustomerRepository(customerDaoMock)

        coEvery { customerDaoMock.getAll() } returns customers

        val result = customerRepository.getCustomers()

        Assert.assertEquals(customers.size, result.size)
    }

    @Test
    fun shouldSaveWhenCustomerIsValid() = runBlocking {
        val customerDaoMock = mockk<CustomerDao>()
        val customerRepository = CustomerRepository(customerDaoMock)

        coEvery { customerDaoMock.save(any()) } just runs

        customerRepository.save(Customer(1, "PDV A", "99999-9999"))

        coVerify(exactly = 1) { customerDaoMock.save(Customer(1, "PDV A", "99999-9999")) }
    }

    @Test
    fun shouldUpdateWhenCustomerIsValid() = runBlocking {
        val customerDaoMock = mockk<CustomerDao>()
        val customerRepository = CustomerRepository(customerDaoMock)

        coEvery { customerDaoMock.update(any()) } returns 1

        customerRepository.update(Customer(1, "PDV A", "99999-9999"))

        coVerify(exactly = 1) { customerDaoMock.update(Customer(1, "PDV A", "99999-9999")) }
    }
}
