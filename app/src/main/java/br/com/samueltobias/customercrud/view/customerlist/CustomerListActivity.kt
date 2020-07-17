package br.com.samueltobias.customercrud.view.customerlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import br.com.samueltobias.customercrud.R
import br.com.samueltobias.customercrud.data.database.AppDatabase
import br.com.samueltobias.customercrud.data.repository.CustomerRepository
import br.com.samueltobias.customercrud.data.repository.asynctask.Callback
import br.com.samueltobias.customercrud.domain.model.Customer
import br.com.samueltobias.customercrud.view.CustomerActivityCommunication
import br.com.samueltobias.customercrud.view.OnClickListener
import br.com.samueltobias.customercrud.view.customerform.CustomerFormActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.coroutines.launch

class CustomerListActivity : AppCompatActivity(), CustomerActivityCommunication {
    private lateinit var repository: CustomerRepository
    private lateinit var customerListAdapter: CustomerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)
        setupScreen()
        setupRepository()
        setupFabButton()
        setupList()
        fetchCustomers()
    }

    private fun setupRepository() {
        repository = CustomerRepository(AppDatabase.getInstance(this)!!.customerDao!!)
    }

    private fun setupScreen() {
        title = getString(R.string.app_name)
    }

    private fun fetchCustomers() {
        lifecycleScope.launch {
            println("Iniciando busca")
            val customers = repository.getCustomers()
            println("Setando ${customers.size ?: 0} customers no adapter")
            customerListAdapter.setCustomers(customers)
            println("Lista populada!")
        }
        println("Fim do método")
    }

    private fun setupList() {
        val recyclerView = findViewById<RecyclerView>(R.id.customer_list_recycler_view)
        customerListAdapter = CustomerListAdapter(this@CustomerListActivity, object : OnClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(this@CustomerListActivity, CustomerFormActivity::class.java)
                intent.putExtra(CustomerActivityCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED, customerListAdapter.getCustomers()[position])
                startActivityForResult(intent, CustomerActivityCommunication.CUSTOMER_EDIT_REQUEST_CODE)
            }
        })
        recyclerView.adapter = customerListAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == CustomerActivityCommunication.CUSTOMER_ADD_REQUEST_CODE && resultCode == CustomerActivityCommunication.CUSTUMER_ADD_RESULT_CODE) || (requestCode == CustomerActivityCommunication.CUSTOMER_EDIT_REQUEST_CODE && resultCode == CustomerActivityCommunication.CUSTUMER_EDIT_RESULT_CODE)) {
            data?.extras?.let {
                val customer = it.getSerializable(CustomerActivityCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED) as Customer
                saveCustomer(customer)
            }
        }
    }

    private fun saveCustomer(customer: Customer) {
        repository.save(customer, object : Callback<Boolean> {
            override fun onFinish(success: Boolean) {
                if (success) {
                    customerListAdapter.add(customer)
                }
            }
        })
    }

    private fun setupFabButton() {
        val floatingActionButton = findViewById<ExtendedFloatingActionButton>(R.id.customer_list_fab_add)
        floatingActionButton.setOnClickListener { startActivityForResult(Intent(this@CustomerListActivity, CustomerFormActivity::class.java), CustomerActivityCommunication.CUSTOMER_ADD_REQUEST_CODE) }
    }
}