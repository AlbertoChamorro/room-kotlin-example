package com.example.roomdbflow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: String,
    var name: String,
    var createdAt: Date,
    var unitPrice: Double,
    @ColumnInfo(name = "discount_percentage")
    var discount: Double,
    var quantity: Int,
    var tax: Double,
    @Ignore var subtotal: Double
)