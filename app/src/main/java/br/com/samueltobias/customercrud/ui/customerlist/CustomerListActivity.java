package br.com.samueltobias.customercrud.ui.customerlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.dao.CustomerDAO;
import br.com.samueltobias.customercrud.model.Customer;
import br.com.samueltobias.customercrud.ui.CustomerConstant;
import br.com.samueltobias.customercrud.ui.customerform.CustomerFormActivity;

public class CustomerListActivity extends AppCompatActivity implements CustomerConstant {

    private final CustomerDAO dao = new CustomerDAO();

    ExtendedFloatingActionButton fab;
    RecyclerView recyclerView;
    CustomerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        setTitle(getString(R.string.app_name));
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CUSTOMER_ADD_REQUEST_CODE && resultCode == CUSTUMER_ADD_RESULT_CODE && data != null && data.hasExtra("customer")) {
            Customer customer = (Customer) data.getExtras().get("customer");

            dao.save(customer);
            adapter.add(customer);
        }
    }

    private void initView() {
        fab = findViewById(R.id.customer_list_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CustomerListActivity.this, CustomerFormActivity.class), CUSTOMER_ADD_REQUEST_CODE);
            }
        });

        recyclerView = findViewById(R.id.customer_list_recycler_view);
        adapter = new CustomerListAdapter(this, dao.getCustomers());
        recyclerView.setAdapter(adapter);
    }
}
