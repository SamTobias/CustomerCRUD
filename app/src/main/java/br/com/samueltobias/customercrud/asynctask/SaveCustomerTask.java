package br.com.samueltobias.customercrud.asynctask;

import android.os.AsyncTask;

import br.com.samueltobias.customercrud.database.dao.CustomerDao;
import br.com.samueltobias.customercrud.model.Customer;

public class SaveCustomerTask extends AsyncTask<Void, Integer, Boolean> {

    private Customer customer;
    private CustomerDao customerDao;
    private Callback<Boolean> listener;

    public SaveCustomerTask(Customer customer, CustomerDao customerDao, Callback<Boolean> listener) {
        this.customer = customer;
        this.customerDao = customerDao;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            customerDao.save(customer);
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
