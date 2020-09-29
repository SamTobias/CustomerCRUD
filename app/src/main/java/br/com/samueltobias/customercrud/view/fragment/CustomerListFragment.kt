package br.com.samueltobias.customercrud.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import br.com.samueltobias.customercrud.R
import br.com.samueltobias.customercrud.data.database.AppDatabase
import br.com.samueltobias.customercrud.data.repository.CustomerRepository
import br.com.samueltobias.customercrud.domain.model.Customer
import br.com.samueltobias.customercrud.view.CustomerCommunication
import br.com.samueltobias.customercrud.view.OnClickListener
import br.com.samueltobias.customercrud.view.adapter.CustomerListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_customer_list.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerListFragment : Fragment(), CustomerCommunication {
    private lateinit var navController: NavController
    private lateinit var repository: CustomerRepository
    private lateinit var customerListAdapter: CustomerListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupRepository()
        setupFabButton()
        setupList()
        fetchCustomers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(CustomerCommunication.CUSTOMER_ADD_REQUEST_CODE) { requestKey: String, bundle: Bundle ->
            val customer = bundle.getSerializable(CustomerCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED) as Customer
            saveCustomer(customer)
        }
        setFragmentResultListener(CustomerCommunication.CUSTOMER_EDIT_REQUEST_CODE) { requestKey: String, bundle: Bundle ->
            val customer = bundle.getSerializable(CustomerCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED) as Customer
            updateCustomer(customer)
        }
    }

    private fun setupRepository() {
        repository = CustomerRepository(AppDatabase.getInstance(requireContext())!!.customerDao!!)
    }

    private fun fetchCustomers() {
        lifecycleScope.launch {
            val customers = repository.getCustomers()
            customerListAdapter.setCustomers(customers)
        }
    }

    private fun setupList() {
        customerListAdapter = CustomerListAdapter(requireContext(), object : OnClickListener {
            override fun onClick(position: Int) {
                navController.navigate(CustomerListFragmentDirections.actionCustomerListFragmentToCustomerFormFragment(customerListAdapter.getCustomers()[position]))
            }
        })
        customer_list_recycler_view.adapter = customerListAdapter
    }

    private fun saveCustomer(customer: Customer) {
        lifecycleScope.launch {
            repository.save(customer)
            fetchCustomers()
        }
    }

    private fun updateCustomer(customer: Customer) {
        lifecycleScope.launch {
            repository.update(customer)
            fetchCustomers()
        }
    }

    private fun setupFabButton() {
        customer_list_fab_add.setOnClickListener {
            navController.navigate(CustomerListFragmentDirections.actionCustomerListFragmentToCustomerFormFragment())
        }
    }
}