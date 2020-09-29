package br.com.samueltobias.customercrud.domain.model

import org.junit.Assert
import org.junit.Test

class CustomerTest {
    @Test
    fun shouldReturnValidCustomer_WhenFillingAllFields() {
        val customer = Customer(1, "Mercado XPTO", "99999-9999")
        Assert.assertTrue(customer.isValid)
    }

    @Test
    fun shouldReturnInvalidCustomer_WhenAllFieldsAreEmpty() {
        val customer = Customer(1, "", "")
        Assert.assertFalse(customer.isValid)
    }

    @Test
    fun shouldReturnInvalidCustomer_WhenAllFieldsAreNull() {
        val customer = Customer(1, null, null)
        Assert.assertFalse(customer.isValid)
    }
}