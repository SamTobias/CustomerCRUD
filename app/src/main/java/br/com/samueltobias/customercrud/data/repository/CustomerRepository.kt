package br.com.samueltobias.customercrud.data.repository

import br.com.samueltobias.customercrud.data.repository.asynctask.Callback
import br.com.samueltobias.customercrud.data.repository.asynctask.SaveCustomerTask
import br.com.samueltobias.customercrud.data.database.dao.CustomerDao
import br.com.samueltobias.customercrud.data.repository.asynctask.UpdateCustomerTask
import br.com.samueltobias.customercrud.domain.model.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomerRepository(private val customerDao: CustomerDao) {
    fun save(customer: Customer, callback: Callback<Boolean>) {
        SaveCustomerTask(customer, customerDao, callback).execute()
    }

    fun update(customer: Customer, callback: Callback<Boolean>) {
        UpdateCustomerTask(customer, customerDao, callback).execute()
    }

    suspend fun getCustomers(): List<Customer> = withContext(Dispatchers.IO) {
        customerDao.getAll()
    }
}