package br.com.samueltobias.customercrud.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.samueltobias.customercrud.model.Customer;

@Dao
public interface CustomerDao {
    @Insert
    void save(Customer customer);

    @Query(value = "SELECT * FROM customer")
    List<Customer> getAll();
}
