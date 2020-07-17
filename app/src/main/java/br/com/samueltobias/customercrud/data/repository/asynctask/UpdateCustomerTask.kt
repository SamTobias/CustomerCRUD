package br.com.samueltobias.customercrud.data.repository.asynctask

import android.os.AsyncTask
import br.com.samueltobias.customercrud.data.database.dao.CustomerDao
import br.com.samueltobias.customercrud.domain.model.Customer

class UpdateCustomerTask(
        private val customer: Customer,
        private val customerDao: CustomerDao,
        private val listener: Callback<Boolean>
) : AsyncTask<Void?, Int?, Boolean>() {
    override fun doInBackground(vararg voids: Void?): Boolean {
        return try {
            customerDao.update(customer)
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