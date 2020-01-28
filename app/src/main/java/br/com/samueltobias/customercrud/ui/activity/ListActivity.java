package br.com.samueltobias.customercrud.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.dao.CustomerDAO;
import br.com.samueltobias.customercrud.model.Customer;

public class ListActivity extends AppCompatActivity {
    private final CustomerDAO dao = new CustomerDAO();

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle(getString(R.string.app_name));

        list = findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1));

        FloatingActionButton button = findViewById(R.id.button_new);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, FormActivity.class));
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer customer = dao.getCustomers().get(position);

                startActivity(new Intent(ListActivity.this, FormActivity.class).putExtra("customer", customer));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getCustomers()));
    }
}
