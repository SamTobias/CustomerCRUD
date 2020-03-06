package br.com.samueltobias.customercrud.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.samueltobias.customercrud.asynctask.RetornoSalvamentoCliente;
import br.com.samueltobias.customercrud.asynctask.SalvaClienteTask;
import br.com.samueltobias.customercrud.model.Customer;

public class CustomerDAO {
    private final static List<Customer> list = new ArrayList<>();

    public void save(final Customer customer, RetornoSalvamentoCliente callbackGravacao) {
        Log.d("ThreadsLog", "Iniciando salvamento");
        new SalvaClienteTask(list, customer, callbackGravacao).execute();

        Log.d("ThreadsLog", "Finalizando salvamento");
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(list);
    }
}
