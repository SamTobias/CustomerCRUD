package br.com.samueltobias.customercrud.domain.model

import org.junit.Assert
import org.junit.Test

class CustomerTest {
    @Test
    fun shouldReturnValidCustomer_WhenFillingAllFields() {
        val customer = Customer("Mercado XPTO", "99999-9999")
        Assert.assertTrue(customer.isValid)
    }

    @Test
    fun shouldReturnInvalidCustomer_WhenAllFieldsAreEmpty() {
        val customer = Customer("", "")
        Assert.assertFalse(customer.isValid)
    }
}