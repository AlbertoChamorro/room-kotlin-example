package com.example.roomdbflow.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdbflow.models.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM products where id = :id")
    fun get(id: Int): LiveData<Product>

    @Insert
    suspend fun add(mode: Product)

    @Update
    suspend fun update(mode: Product)

    @Query("SELECT * FROM products ORDER BY id ASC")
    fun getAll(): LiveData<List<Product>>

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(model: Product)
}