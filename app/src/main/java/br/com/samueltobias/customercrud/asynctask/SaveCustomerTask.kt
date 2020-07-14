package br.com.samueltobias.customercrud.asynctask

import android.os.AsyncTask
import br.com.samueltobias.customercrud.database.dao.CustomerDao
import br.com.samueltobias.customercrud.model.Customer

class SaveCustomerTask(
        private val customer: Customer,
        private val customerDao: CustomerDao,
        private val listener: Callback<Boolean>
) : AsyncTask<Void?, Int?, Boolean>() {
    override fun doInBackground(vararg voids: Void?): Boolean {
        return try {
            customerDao.save(customer)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun onPostExecute(aBoolean: Boolean) {
        super.onPostExecute(aBoolean)
        listener.onFinish(aBoolean)
    }

}