package br.com.samueltobias.customercrud.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class BuscaClientesTask extends AsyncTask<Void, Void, List<Customer>> {
    private CustomerDao customerDao;
    private RetornoAcaoDao<List<Customer>> callbackRetorno;

    public BuscaClientesTask(CustomerDao customerDao, RetornoAcaoDao<List<Customer>> callbackRetorno) {
        this.customerDao = customerDao;
        this.callbackRetorno = callbackRetorno;
    }

    @Override
    protected List<Customer> doInBackground(Void... voids) {
        return customerDao.getAll();
    }

    @Override
    protected void onPostExecute(List<Customer> customers) {
        super.onPostExecute(customers);

        callbackRetorno.quandoTermina(customers);
    }
}
