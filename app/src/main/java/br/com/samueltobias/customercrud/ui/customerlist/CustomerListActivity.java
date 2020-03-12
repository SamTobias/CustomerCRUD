package br.com.samueltobias.customercrud.ui.customerlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.asynctask.RetornoSalvamentoCliente;
import br.com.samueltobias.customercrud.repository.CustomerRepository;
import br.com.samueltobias.customercrud.model.Customer;
import br.com.samueltobias.customercrud.ui.CustomerActivityCommunication;
import br.com.samueltobias.customercrud.ui.OnClickListener;
import br.com.samueltobias.customercrud.database.AppDatabase;
import br.com.samueltobias.customercrud.ui.customerform.CustomerFormActivity;

public class CustomerListActivity extends AppCompatActivity implements CustomerActivityCommunication {

    private final CustomerRepository dao = new CustomerRepository();

    ExtendedFloatingActionButton fab;
    RecyclerView recyclerView;
    private CustomerListAdapter adapter;

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

        if (requestCode == CUSTOMER_ADD_REQUEST_CODE && resultCode == CUSTUMER_ADD_RESULT_CODE && data != null && data.hasExtra(INTENT_EXTRA_CUSTOMER)) {
            final Customer customer = (Customer) data.getExtras().get(INTENT_EXTRA_CUSTOMER);

            dao.save(this, customer, new RetornoSalvamentoCliente() {
                @Override
                public void quandoTermina(boolean sucesso) {
                    if (sucesso) {
                        AppDatabase.getInstance(CustomerListActivity.this).getCustomerDao().save(customer);
                        adapter.add(customer);
                    }
                }
            });
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

        List<Customer> customerList = AppDatabase.getInstance(this).getCustomerDao().getAll();
        adapter = new CustomerListAdapter(this, customerList, new OnClickListener() {
            @Override
            public void quandoClicar(int posicao) {
                Toast.makeText(CustomerListActivity.this, "", Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
