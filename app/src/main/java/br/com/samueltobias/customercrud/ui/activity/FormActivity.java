package br.com.samueltobias.customercrud.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.samueltobias.customercrud.R;
import br.com.samueltobias.customercrud.dao.CustomerDAO;
import br.com.samueltobias.customercrud.model.Customer;

public class FormActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        final CustomerDAO dao = new CustomerDAO();

        final EditText nameEdit = findViewById(R.id.edit_name);
        final EditText phoneEdit = findViewById(R.id.edit_phone);
        Button button = findViewById(R.id.button_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer(nameEdit.getText().toString(), phoneEdit.getText().toString());
                dao.save(customer);

                finish();
            }
        });


    }
}
