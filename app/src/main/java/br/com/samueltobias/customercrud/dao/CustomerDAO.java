package br.com.samueltobias.customercrud.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.samueltobias.customercrud.model.Customer;

public class CustomerDAO {
    private final static List<Customer> list = new ArrayList<>();

    public void save(Customer customer) {
        list.add(customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(list);
    }
}
