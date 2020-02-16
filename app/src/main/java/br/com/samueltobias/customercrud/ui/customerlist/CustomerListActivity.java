package br.com.samueltobias.customercrud.ui.customerlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.dao.CustomerDAO;
import br.com.samueltobias.customercrud.database.AppDatabase;
import br.com.samueltobias.customercrud.model.Customer;
import br.com.samueltobias.customercrud.ui.customerform.CustomerFormActivity;

public class CustomerListActivity extends AppCompatActivity {

    private final CustomerDAO dao = new CustomerDAO();

    ExtendedFloatingActionButton fab;
    RecyclerView recyclerView;
    private CustomerListAdapter adapter;
    private List<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        setTitle(getString(R.string.app_name));
        initView();
    }

    private void initView() {
        fab = findViewById(R.id.customer_list_fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerListActivity.this, CustomerFormActivity.class));
            }
        });

        recyclerView = findViewById(R.id.customer_list_recycler_view);

        customerList = AppDatabase.getInstance(this).getCustomerDao().getAll();
        adapter = new CustomerListAdapter(this, customerList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        customerList = AppDatabase.getInstance(this).getCustomerDao().getAll();

        adapter.notifyDataSetChanged();
    }
}
