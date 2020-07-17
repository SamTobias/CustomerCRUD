package br.com.samueltobias.customercrud.data.repository

import br.com.samueltobias.customercrud.data.repository.asynctask.Callback
import br.com.samueltobias.customercrud.data.repository.asynctask.SaveCustomerTask
import br.com.samueltobias.customercrud.data.database.dao.CustomerDao
import br.com.samueltobias.customercrud.domain.model.Customer

class CustomerRepository(private val customerDao: CustomerDao) {
    fun save(customer: Customer, callback: Callback<Boolean>) {
        SaveCustomerTask(customer, customerDao, callback).execute()
    }

    suspend fun getCustomers() : List<Customer> {
        return customerDao.getAll()
    }
}