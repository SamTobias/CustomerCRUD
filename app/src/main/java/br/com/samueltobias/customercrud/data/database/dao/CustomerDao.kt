package br.com.samueltobias.customercrud.data.database.dao

import androidx.room.*
import br.com.samueltobias.customercrud.domain.model.Customer

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(customer: Customer)

    @Update
    fun update(customer: Customer) : Int

    @Query(value = "SELECT * FROM customer")
    fun getAll(): List<Customer>

    @Query(value = "SELECT * FROM customer WHERE id = :id")
    fun get(id: Long): Customer?
}