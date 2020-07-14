package br.com.samueltobias.customercrud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.samueltobias.customercrud.database.dao.CustomerDao
import br.com.samueltobias.customercrud.model.Customer

@Database(entities = [Customer::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val customerDao: CustomerDao?

    companion object {
        private const val DATABASE_NAME = "app_database"
        private var instance: AppDatabase? = null
        @JvmStatic
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance
        }
    }
}