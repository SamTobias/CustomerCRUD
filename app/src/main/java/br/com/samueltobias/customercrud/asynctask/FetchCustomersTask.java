package br.com.samueltobias.customercrud.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class FetchCustomersTask extends AsyncTask<Void, Void, List<Customer>> {
    private CustomerDao customerDao;
    private Callback<List<Customer>> callback;

    public FetchCustomersTask(CustomerDao customerDao, Callback<List<Customer>> callback) {
        this.customerDao = customerDao;
        this.callback = callback;
    }

    @Override
    protected List<Customer> doInBackground(Void... voids) {
        return customerDao.getAll();
    }

    @Override
    protected void onPostExecute(List<Customer> customers) {
        super.onPostExecute(customers);

        callback.onFinish(customers);
    }
}
