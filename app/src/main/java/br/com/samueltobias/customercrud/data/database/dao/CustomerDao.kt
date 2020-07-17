package br.com.samueltobias.customercrud.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.samueltobias.customercrud.domain.model.Customer

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(customer: Customer)

    @Query(value = "SELECT * FROM customer")
    suspend fun getAll(): List<Customer>

    @Query(value = "SELECT * FROM customer WHERE id = :id")
    fun get(id: Long): Customer?
}