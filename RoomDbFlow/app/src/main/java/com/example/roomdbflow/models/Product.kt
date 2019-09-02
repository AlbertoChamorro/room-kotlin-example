package com.example.roomdbflow.models

import androidx.room.*
import org.jetbrains.annotations.NotNull
import java.sql.Date

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Long,
    var name: String,
    var createdAt: Date,
    var unitPrice: Double,
    @ColumnInfo(name = "discount_percentage")
    var discount: Double,
    var quantity: Int,
    var tax: Double,
    @Ignore var subtotal: Double
) {
    constructor(
        id: Long,
        name: String,
        createdAt: Date,
        unitPrice: Double,
        discount: Double,
        quantity: Int,
        tax: Double
    ) : this(id, name, createdAt, unitPrice, discount, quantity, tax, (unitPrice * quantity) * (1 - discount + tax))
}