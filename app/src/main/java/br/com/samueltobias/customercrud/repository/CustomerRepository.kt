package br.com.samueltobias.customercrud.repository

import br.com.samueltobias.customercrud.asynctask.Callback
import br.com.samueltobias.customercrud.asynctask.FetchCustomersTask
import br.com.samueltobias.customercrud.asynctask.SaveCustomerTask
import br.com.samueltobias.customercrud.database.dao.CustomerDao
import br.com.samueltobias.customercrud.model.Customer

class CustomerRepository(private val customerDao: CustomerDao) {
    fun save(customer: Customer, callback: Callback<Boolean>) {
        SaveCustomerTask(customer, customerDao, callback).execute()
    }

    fun getCustomers(callback: Callback<List<Customer>>) {
        FetchCustomersTask(customerDao, callback).execute()
    }
}