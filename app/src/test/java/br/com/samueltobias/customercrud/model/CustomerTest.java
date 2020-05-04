package br.com.samueltobias.customercrud.model;

import org.junit.Assert;
import org.junit.Test;

import br.com.samueltobias.customercrud.model.Customer;

public class CustomerTest {

    @Test
    public void shouldReturnValidCustomer_WhenFillingAllFields() {
        Customer customer = new Customer("Mercado XPTO", "99999-9999");

        Assert.assertTrue(customer.isValid());
    }

    @Test
    public void shouldReturnInvalidCustomer_WhenAllFieldsAreEmpty() {
        Customer customer = new Customer("", "");

        Assert.assertFalse(customer.isValid());
    }
}