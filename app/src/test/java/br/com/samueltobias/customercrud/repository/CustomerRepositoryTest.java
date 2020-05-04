package br.com.samueltobias.customercrud.repository;

import org.junit.Assert;
import org.junit.Test;

import br.com.samueltobias.customercrud.model.Customer;

public class CustomerRepositoryTest {

    @Test
    public void shouldReturnValidCustomer_WhenFillingAllFields() {
        Customer customer = new Customer("Mercado XPTO", "9999-9999");

        Assert.assertTrue(customer.isValid());
    }

    @Test
    public void shouldReturnInvalidCustomer_WhenAllFieldsAreEmpty() {
        Customer customer = new Customer("", "");

        Assert.assertFalse(customer.isValid());
    }
}