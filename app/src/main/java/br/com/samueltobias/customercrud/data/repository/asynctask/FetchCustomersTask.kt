package br.com.samueltobias.customercrud.data.repository.asynctask

import android.os.AsyncTask
import br.com.samueltobias.customercrud.data.database.dao.CustomerDao
import br.com.samueltobias.customercrud.domain.model.Customer

//class FetchCustomersTask(
//        private val customerDao: CustomerDao,
//        private val callback: Callback<List<Customer>>
//) : AsyncTask<Void?, Void?, List<Customer>>() {
//    override fun doInBackground(vararg voids: Void?): List<Customer> {
//        return customerDao.getAll()
//    }
//
//    override fun onPostExecute(customers: List<Customer>) {
//        super.onPostExecute(customers)
//        callback.onFinish(customers)
//    }
//
//}