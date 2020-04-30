package br.com.samueltobias.customercrud.asynctask;

import android.os.AsyncTask;

import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class SaveCustomerTask extends AsyncTask<Void, Integer, Boolean> {

    private Customer customer;
    private CustomerDao dao;
    private Callback<Boolean> listener;

    public SaveCustomerTask(Customer customer, CustomerDao dao, Callback<Boolean> listener) {
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

        listener.onFinish(aBoolean);
    }
}
