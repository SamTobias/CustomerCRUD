package br.com.samueltobias.customercrud.asynctask;

import android.os.AsyncTask;

import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class SalvaClienteTask extends AsyncTask<Void, Integer, Boolean> {

    private Customer customer;
    private CustomerDao dao;
    private RetornoAcaoDao<Boolean> listener;

    public SalvaClienteTask(Customer customer, CustomerDao dao, RetornoAcaoDao<Boolean> listener) {
        this.customer = customer;
        this.dao = dao;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            dao.save(customer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        listener.quandoTermina(aBoolean);
    }
}
