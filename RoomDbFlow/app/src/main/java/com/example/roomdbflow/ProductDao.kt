package com.example.roomdbflow

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface ProductDao {

    @Query("SELECT * FROM products where id LIKE :id")
    fun get(id: Int): Product

    @Insert
    fun add(mode: Product)

    @Update
    fun update(mode: Product)

    @Query("SELECT * FROM products")
    fun get(): List<Product>
}