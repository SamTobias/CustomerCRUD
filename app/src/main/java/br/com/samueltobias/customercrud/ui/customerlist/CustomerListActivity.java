package br.com.samueltobias.customercrud.ui.customerlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.asynctask.Callback;
import br.com.samueltobias.customercrud.database.AppDatabase;
import br.com.samueltobias.customercrud.model.Customer;
import br.com.samueltobias.customercrud.repository.CustomerRepository;
import br.com.samueltobias.customercrud.ui.CustomerActivityCommunication;
import br.com.samueltobias.customercrud.ui.OnClickListener;
import br.com.samueltobias.customercrud.ui.customerform.CustomerFormActivity;

public class CustomerListActivity extends AppCompatActivity implements CustomerActivityCommunication {

    private CustomerRepository repository;
    private CustomerListAdapter customerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        setTitle(getString(R.string.app_name));

        repository = new CustomerRepository(AppDatabase.getInstance(this).getCustomerDao());

        setupFabButton();
        setupList();
        fetchCustomers();
    }

    private void fetchCustomers() {
        repository.getCustomers(new Callback<List<Customer>>() {
            @Override
            public void onFinish(final List<Customer> customers) {
                customerListAdapter.setCustomers(customers);
            }
        });
    }

    private void setupList() {
        RecyclerView recyclerView = findViewById(R.id.customer_list_recycler_view);
        customerListAdapter = new CustomerListAdapter(CustomerListActivity.this, new OnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(CustomerListActivity.this, CustomerFormActivity.class);
                intent.putExtra(INTENT_EXTRA_CUSTOMER_SERIALIZED, customerListAdapter.getCustomers().get(position));
                startActivityForResult(intent, CUSTOMER_EDIT_REQUEST_CODE);
            }
        });
        recyclerView.setAdapter(customerListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CUSTOMER_ADD_REQUEST_CODE && resultCode == CUSTUMER_ADD_RESULT_CODE && data != null && data.hasExtra(INTENT_EXTRA_CUSTOMER_SERIALIZED)) {
            final Customer customer = (Customer) data.getExtras().get(INTENT_EXTRA_CUSTOMER_SERIALIZED);

            repository.save(customer, new Callback<Boolean>() {
                @Override
                public void onFinish(Boolean success) {
                    if (success) {
                        customerListAdapter.add(customer);
                    }
                }
            });
        }
    }

    private void setupFabButton() {
        ExtendedFloatingActionButton floatingActionButton = findViewById(R.id.customer_list_fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CustomerListActivity.this, CustomerFormActivity.class), CUSTOMER_ADD_REQUEST_CODE);
            }
        });
    }
}
