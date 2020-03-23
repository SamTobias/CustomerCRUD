package br.com.samueltobias.customercrud.repository;

import java.util.List;

import br.com.samueltobias.customercrud.asynctask.BuscaClientesTask;
import br.com.samueltobias.customercrud.asynctask.RetornoAcaoDao;
import br.com.samueltobias.customercrud.asynctask.SalvaClienteTask;
import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class CustomerRepository {

    private CustomerDao customerDao;

    public CustomerRepository(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void save(final Customer customer, RetornoAcaoDao<Boolean> callbackGravacao) {
        new SalvaClienteTask(customer, customerDao, callbackGravacao).execute();
    }

    public void getClientes(RetornoAcaoDao<List<Customer>> callbackRetorno) {
        new BuscaClientesTask(customerDao, callbackRetorno).execute();
    }
}
