package com.example.roomdbflow

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = BuildConfig.VERSION_CODE)
abstract class CbcDatabase : RoomDatabase() {

    abstract val productDao: ProductDao

    companion object {
        @Volatile
        private var INSTANCE: CbcDatabase? = null

        fun getDatabase(context: Context): CbcDatabase {

            INSTANCE?.let { instance ->
                return instance
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        CbcDatabase::class.java,
                        "cbc-database-develop"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}