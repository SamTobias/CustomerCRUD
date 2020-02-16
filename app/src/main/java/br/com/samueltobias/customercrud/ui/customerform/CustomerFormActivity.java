package br.com.samueltobias.customercrud.ui.customerform;

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

public class CustomerFormActivity extends AppCompatActivity {
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
        if (item.getItemId() == R.id.customer_form_menu_save) {
            AppDatabase.getInstance(this).getCustomerDao().save(new Customer(Objects.requireNonNull(nameEdit.getText()).toString(), Objects.requireNonNull(phoneEdit.getText()).toString()));

            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
