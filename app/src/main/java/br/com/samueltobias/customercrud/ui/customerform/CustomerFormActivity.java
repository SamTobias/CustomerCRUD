package br.com.samueltobias.customercrud.ui.customerform;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.dao.CustomerDAO;
import br.com.samueltobias.customercrud.model.Customer;

public class CustomerFormActivity extends AppCompatActivity {
    private final CustomerDAO dao = new CustomerDAO();
    private EditText nameEdit;
    private EditText phoneEdit;

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
        if (item.getItemId() == R.id.customer_form_menu_save) {
            dao.save(new Customer(nameEdit.getText().toString(), phoneEdit.getText().toString()));

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
