package br.com.samueltobias.customercrud.repository;

import java.util.List;

import br.com.samueltobias.customercrud.asynctask.FetchCustomersTask;
import br.com.samueltobias.customercrud.asynctask.Callback;
import br.com.samueltobias.customercrud.asynctask.SaveCustomerTask;
import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class CustomerRepository {

    private CustomerDao customerDao;

    public CustomerRepository(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void save(final Customer customer, Callback<Boolean> callback) {
        new SaveCustomerTask(customer, customerDao, callback).execute();
    }

    public void getCustomers(Callback<List<Customer>> callback) {
        new FetchCustomersTask(customerDao, callback).execute();
    }
}
