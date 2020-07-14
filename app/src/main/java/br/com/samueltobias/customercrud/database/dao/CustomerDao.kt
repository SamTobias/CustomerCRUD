package br.com.samueltobias.customercrud.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.samueltobias.customercrud.model.Customer

@Dao
interface CustomerDao {
    @Insert
    fun save(customer: Customer)

    @Query(value = "SELECT * FROM customer")
    fun getAll(): List<Customer>

    @Query(value = "SELECT * FROM customer WHERE id = :id")
    fun get(id: Long): Customer?
}