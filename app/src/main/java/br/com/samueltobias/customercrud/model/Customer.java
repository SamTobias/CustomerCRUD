package br.com.samueltobias.customercrud.model;

import androidx.annotation.NonNull;

public class Customer {

    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
