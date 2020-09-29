package br.com.samueltobias.customercrud.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Customer(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val name: String?,
        val phone: String?
) : Serializable {

    val isValid: Boolean
        get() {
            if (name == null || name.isEmpty()) {
                return false
            }
            return !(phone == null || phone.isEmpty())
        }
}