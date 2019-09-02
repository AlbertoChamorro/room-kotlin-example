package com.example.roomdbflow.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdbflow.repository.ProductRepository
import com.example.roomdbflow.database.CbcDatabase
import com.example.roomdbflow.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository

    val products: LiveData<List<Product>>

    init {
        var productDao = CbcDatabase.getDatabase(application, viewModelScope).productDao
        repository = ProductRepository(productDao)
        products = repository.products
    }

    fun add(product: Product) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(product)
        }
}