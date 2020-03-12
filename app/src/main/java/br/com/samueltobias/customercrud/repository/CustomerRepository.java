package br.com.samueltobias.customercrud.repository;

import android.content.Context;

import br.com.samueltobias.customercrud.asynctask.RetornoSalvamentoCliente;
import br.com.samueltobias.customercrud.asynctask.SalvaClienteTask;
import br.com.samueltobias.customercrud.database.AppDatabase;
import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class CustomerRepository {
    public void save(Context context, final Customer customer, RetornoSalvamentoCliente callbackGravacao) {
        CustomerDao dao = AppDatabase.getInstance(context).getCustomerDao();
        new SalvaClienteTask(customer, dao, callbackGravacao).execute();
    }
}
