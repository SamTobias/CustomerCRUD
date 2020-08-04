package br.com.samueltobias.customercrud.data.repository

import br.com.samueltobias.customercrud.data.database.dao.CustomerDao
import br.com.samueltobias.customercrud.domain.model.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomerRepository(private val customerDao: CustomerDao) {
    suspend fun save(customer: Customer)  = withContext(Dispatchers.IO) {
        customerDao.save(customer)
    }

    suspend fun update(customer: Customer)  = withContext(Dispatchers.IO) {
        customerDao.update(customer)
    }

    suspend fun getCustomers(): List<Customer> = withContext(Dispatchers.IO) {
        customerDao.getAll()
    }
}