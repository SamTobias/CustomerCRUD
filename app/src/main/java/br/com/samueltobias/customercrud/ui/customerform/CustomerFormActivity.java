package br.com.samueltobias.customercrud.ui.customerform;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.model.Customer;
import br.com.samueltobias.customercrud.ui.CustomerActivityCommunication;

public class CustomerFormActivity extends AppCompatActivity implements CustomerActivityCommunication {
    private TextInputEditText nameEdit;
    private TextInputEditText phoneEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        initView();

        if (getIntent().getExtras() != null) {
            Customer customer = (Customer) getIntent().getExtras().get(INTENT_EXTRA_CUSTOMER_SERIALIZED);
            if (customer != null) {
                nameEdit.setText(customer.getName());
                phoneEdit.setText(customer.getPhone());
            }
        }
    }

    private void initView() {
        nameEdit = findViewById(R.id.edit_name);
        phoneEdit = findViewById(R.id.edit_phone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.customer_form_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.customer_form_menu_save) {
            saveCustomer();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveCustomer() {
        Customer customer = populateCustomer();

        if (!customer.isValid()) {
            Toast.makeText(this, R.string.invalid_customer, Toast.LENGTH_LONG).show();
            return;
        }

        redirectToList(customer);
    }

    private Customer populateCustomer() {
        String name  = nameEdit.getText() != null ? nameEdit.getText().toString() : null;
        String phone = phoneEdit.getText() != null ? phoneEdit.getText().toString() : null;

        return new Customer(name, phone);
    }

    private void redirectToList(Customer customer) {
        Intent intent = new Intent().putExtra(INTENT_EXTRA_CUSTOMER_SERIALIZED, customer);
        setResult( CustomerActivityCommunication.CUSTUMER_ADD_RESULT_CODE, intent );
        finish();
    }
}
