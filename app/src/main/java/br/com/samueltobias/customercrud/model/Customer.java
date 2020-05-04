package br.com.samueltobias.customercrud.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Customer implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;
    private String phone;

    public Customer() {
    }

    @Ignore
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            return false;
        }

        if (phone == null || phone.isEmpty()) {
            return false;
        }

        return true;
    }
}
