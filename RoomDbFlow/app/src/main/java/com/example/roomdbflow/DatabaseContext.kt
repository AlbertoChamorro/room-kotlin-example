package com.example.roomdbflow

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = BuildConfig.VERSION_CODE)
abstract class DatabaseContext : RoomDatabase() {
    abstract val productDao: ProductDao
}