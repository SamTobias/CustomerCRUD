package br.com.samueltobias.customercrud.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.dao.CustomerDAO;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle(getString(R.string.app_name));

        ListView list = findViewById(R.id.list);

        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1));

        FloatingActionButton button = findViewById(R.id.button_new);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, FormActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        CustomerDAO dao = new CustomerDAO();

        ListView list = findViewById(R.id.list);

        list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getCustomers()));
    }
}
