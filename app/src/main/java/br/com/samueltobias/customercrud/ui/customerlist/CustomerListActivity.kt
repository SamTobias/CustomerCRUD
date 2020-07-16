package br.com.samueltobias.customercrud.ui.customerlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.samueltobias.customercrud.R
import br.com.samueltobias.customercrud.asynctask.Callback
import br.com.samueltobias.customercrud.database.AppDatabase.Companion.getInstance
import br.com.samueltobias.customercrud.model.Customer
import br.com.samueltobias.customercrud.repository.CustomerRepository
import br.com.samueltobias.customercrud.ui.CustomerActivityCommunication
import br.com.samueltobias.customercrud.ui.OnClickListener
import br.com.samueltobias.customercrud.ui.customerform.CustomerFormActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class CustomerListActivity : AppCompatActivity(), CustomerActivityCommunication {
    private var repository: CustomerRepository? = null
    private var customerListAdapter: CustomerListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)
        title = getString(R.string.app_name)
        repository = CustomerRepository(getInstance(this)!!.customerDao!!)
        setupFabButton()
        setupList()
        fetchCustomers()
    }

    private fun fetchCustomers() {
        repository!!.getCustomers(object : Callback<List<Customer>> {
            override fun onFinish(customers: List<Customer>) {
                customerListAdapter!!.setCustomers(customers)
            }
        })
    }

    private fun setupList() {
        val recyclerView = findViewById<RecyclerView>(R.id.customer_list_recycler_view)
        customerListAdapter = CustomerListAdapter(this@CustomerListActivity, object : OnClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(this@CustomerListActivity, CustomerFormActivity::class.java)
                intent.putExtra(CustomerActivityCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED, customerListAdapter!!.getCustomers()[position])
                startActivityForResult(intent, CustomerActivityCommunication.CUSTOMER_EDIT_REQUEST_CODE)
            }
        })
        recyclerView.adapter = customerListAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CustomerActivityCommunication.CUSTOMER_ADD_REQUEST_CODE && resultCode == CustomerActivityCommunication.CUSTUMER_ADD_RESULT_CODE && data != null && data.hasExtra(CustomerActivityCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED)) {
            intent.extras?.let {
                val customer = it.getSerializable(CustomerActivityCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED) as Customer
                repository!!.save(customer, object : Callback<Boolean> {
                    override fun onFinish(success: Boolean) {
                        if (success) {
                            customerListAdapter!!.add(customer)
                        }
                    }
                })
            }
        }
    }

    private fun setupFabButton() {
        val floatingActionButton = findViewById<ExtendedFloatingActionButton>(R.id.customer_list_fab_add)
        floatingActionButton.setOnClickListener { startActivityForResult(Intent(this@CustomerListActivity, CustomerFormActivity::class.java), CustomerActivityCommunication.CUSTOMER_ADD_REQUEST_CODE) }
    }
}