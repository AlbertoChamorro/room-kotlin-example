package com.example.roomdbflow

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM products where id LIKE :id ORDER BY id ASC")
    fun get(id: Int): LiveData<Product>

    @Insert
    suspend fun add(mode: Product)

    @Update
    suspend fun update(mode: Product)

    @Query("SELECT * FROM products")
    fun get(): LiveData<List<Product>>

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(model: Product)
}