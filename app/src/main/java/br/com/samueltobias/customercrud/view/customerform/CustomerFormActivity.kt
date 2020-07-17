package br.com.samueltobias.customercrud.view.customerform

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.samueltobias.customercrud.R
import br.com.samueltobias.customercrud.domain.model.Customer
import br.com.samueltobias.customercrud.view.CustomerActivityCommunication
import com.google.android.material.textfield.TextInputEditText

class CustomerFormActivity : AppCompatActivity(), CustomerActivityCommunication {
    private lateinit var nameEdit: TextInputEditText
    private lateinit var phoneEdit: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_form)
        initView()
        intent.extras?.let {
            val customer = it.getSerializable(CustomerActivityCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED) as Customer
            nameEdit.setText(customer.name)
            phoneEdit.setText(customer.phone)
        }
    }

    private fun initView() {
        nameEdit = findViewById(R.id.edit_name)
        phoneEdit = findViewById(R.id.edit_phone)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        MenuInflater(this).inflate(R.menu.customer_form_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.customer_form_menu_save) {
            saveCustomer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveCustomer() {
        val customer = populateCustomer()
        if (!customer.isValid) {
            Toast.makeText(this, R.string.invalid_customer, Toast.LENGTH_LONG).show()
            return
        }
        redirectToList(customer)
    }

    private fun populateCustomer(): Customer {
        val name = nameEdit.text?.toString()
        val phone = phoneEdit.text?.toString()
        return Customer(name, phone)
    }

    private fun redirectToList(customer: Customer) {
        val intent = intent.putExtra(CustomerActivityCommunication.INTENT_EXTRA_CUSTOMER_SERIALIZED, customer)
        setResult(CustomerActivityCommunication.CUSTOMER_SUCCESS_RESULT_CODE, intent)
        finish()
    }
}