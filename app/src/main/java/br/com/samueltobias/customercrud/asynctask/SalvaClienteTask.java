package br.com.samueltobias.customercrud.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import br.com.samueltobias.customercrud.model.Customer;

public class SalvaClienteTask extends AsyncTask<Void, Integer, Boolean> {


    private List<Customer> list;
    private Customer customer;
    private RetornoSalvamentoCliente listener;

    public SalvaClienteTask(List<Customer> list, Customer customer, RetornoSalvamentoCliente listener) {
        this.list = list;

        this.customer = customer;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        Log.d("ThreadsLog", "doInBackground");
        publishProgress(10);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress(90);

        Log.d("ThreadsLog", "terminou de dormir");

        return list.add(customer);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        Log.d("ThreadsLog", "Progresso" + Arrays.toString(values));
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);


        Log.d("Threads", "onPostExecute");

        listener.quandoTermina(aBoolean);
    }
}
