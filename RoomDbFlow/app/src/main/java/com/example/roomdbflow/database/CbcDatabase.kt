package com.example.roomdbflow.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdbflow.BuildConfig
import com.example.roomdbflow.models.Product
import com.example.roomdbflow.database.dao.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Product::class], version = BuildConfig.VERSION_CODE)
abstract class CbcDatabase : RoomDatabase() {

    abstract val productDao: ProductDao

    companion object {
        @Volatile
        private var INSTANCE: CbcDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CbcDatabase {

            INSTANCE?.let { instance ->
                return instance
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        CbcDatabase::class.java,
                        "cbc-database-develop"
                    ).addCallback(CbcDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class CbcDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.productDao)
                }
            }
        }

        fun populateDatabase(productDao: ProductDao) {
            suspend {
                productDao.deleteAll()
            }

            val catProd1 = Product(1, "Product 1", Date(), 100.5, 0.0, 10, 10.0, 10000.25)
            suspend { productDao.add(catProd1) }
        }
    }
}