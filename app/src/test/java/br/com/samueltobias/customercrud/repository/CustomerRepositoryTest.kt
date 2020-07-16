package br.com.samueltobias.customercrud.repository

import br.com.samueltobias.customercrud.asynctask.Callback
import br.com.samueltobias.customercrud.asynctask.FetchCustomersTask
import br.com.samueltobias.customercrud.database.dao.CustomerDao
import br.com.samueltobias.customercrud.model.Customer
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class CustomerRepositoryTest {
    @Test
    fun fetch() {
        val customers: MutableList<Customer> = ArrayList()
        customers.add(Customer("PDV A", "99999-9999"))
        customers.add(Customer("PDV B", "99999-9999"))
        val customerDaoMock = mockk<CustomerDao>()
        val taskMock = mockk<FetchCustomersTask>()
        every { taskMock.execute() } returns null
        val customerRepository = Mockito.spy(CustomerRepository(customerDaoMock))
        every { customerRepository.getCustomers(any()) } answers {
            lastArg<Callback<List<Customer>>>().onFinish(customers)
        }
        every { customerDaoMock.getAll() } returns customers
        customerRepository.getCustomers(object : Callback<List<Customer>> {
            override fun onFinish(list: List<Customer>) {
                Assert.assertEquals(customers.size.toLong(), list.size.toLong())
            }
        })
    }
}
