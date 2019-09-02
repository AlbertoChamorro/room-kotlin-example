package com.example.roomdbflow.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.roomdbflow.models.Product
import com.example.roomdbflow.database.dao.ProductDao

class ProductRepository(private val productDao: ProductDao) {

    val products: LiveData<List<Product>> = productDao.getAll()

    @WorkerThread
    suspend fun add(product: Product) {
        productDao.add(product)
    }
}