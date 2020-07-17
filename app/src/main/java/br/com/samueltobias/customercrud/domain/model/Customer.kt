package br.com.samueltobias.customercrud.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Customer() : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var name: String? = null
    var phone: String? = null

    @Ignore
    constructor(name: String?, phone: String?) : this() {
        this.name = name
        this.phone = phone
    }

    override fun toString(): String {
        return name!!
    }

    val isValid: Boolean
        get() {
            if (name == null || name!!.isEmpty()) {
                return false
            }
            return !(phone == null || phone!!.isEmpty())
        }
}