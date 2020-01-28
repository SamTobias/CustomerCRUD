package br.com.samueltobias.customercrud.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Customer implements Serializable {

    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
