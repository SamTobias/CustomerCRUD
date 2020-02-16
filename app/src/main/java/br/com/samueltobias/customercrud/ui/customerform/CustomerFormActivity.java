package br.com.samueltobias.customercrud.ui.customerform;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.dao.CustomerDAO;
import br.com.samueltobias.customercrud.database.AppDatabase;
import br.com.samueltobias.customercrud.model.Customer;
import br.com.samueltobias.customercrud.ui.CustomerConstant;

public class CustomerFormActivity extends AppCompatActivity implements CustomerConstant {
    private final CustomerDAO dao = new CustomerDAO();
    private TextInputEditText nameEdit;
    private TextInputEditText phoneEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        initView();

        if (getIntent().getExtras() != null) {
            Customer customer = (Customer) getIntent().getExtras().get("customer");
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
        Intent intent = new Intent().putExtra("customer", new Customer(nameEdit.getText().toString(), phoneEdit.getText().toString()));

        if (item.getItemId() == R.id.customer_form_menu_save) {
            setResult( CustomerConstant.CUSTUMER_ADD_RESULT_CODE, intent );
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
