package br.com.samueltobias.customercrud.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import br.com.samueltobias.customercrud.R
import br.com.samueltobias.customercrud.domain.model.Customer
import br.com.samueltobias.customercrud.view.CustomerCommunication
import kotlinx.android.synthetic.main.fragment_customer_form.*

class CustomerFormFragment : Fragment(), CustomerCommunication {
    private lateinit var navController: NavController
    private val args: CustomerFormFragmentArgs by navArgs()
    private var customerId = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_customer_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)
        setHasOptionsMenu(true)
        args.customer?.let {
            customerId = it.id
            edit_name.setText(it.name)
            edit_phone.setText(it.phone)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.customer_form_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.customer_form_menu_save) {
            hideKeyboard()
            saveCustomer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        view?.let { v ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    private fun saveCustomer() {
        val customer = populateCustomer()
        if (!customer.isValid) {
            Toast.makeText(context, R.string.invalid_customer, Toast.LENGTH_LONG).show()
            return
        }
        redirectToList(customer)
    }

    private fun populateCustomer(): Customer {
        val name = edit_name.text?.toString()
        val phone = edit_phone.text?.toString()
        return Customer(customerId, name, phone)
    }

    private fun redirectToList(customer: Customer) {
        val result = Bundle().apply {
            putSerializable(CustomerCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED, customer)
        }
        setFragmentResult(if (customerId == 0L) CustomerCommunication.CUSTOMER_ADD_REQUEST_CODE else CustomerCommunication.CUSTOMER_EDIT_REQUEST_CODE, result)
        navController.navigateUp()
    }
}