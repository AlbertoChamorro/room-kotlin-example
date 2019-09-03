package com.example.roomdbflow.models

import androidx.room.*
import com.example.roomdbflow.database.converters.DateConverter
import org.jetbrains.annotations.NotNull
import org.joda.time.DateTime

@Entity(tableName = "products")
@TypeConverters(DateConverter::class)
data class Product(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Long,
    var name: String,
    var createdAt: DateTime,
    var unitPrice: Double,
    @ColumnInfo(name = "discount_percentage")
    var discount: Double,
    var quantity: Int,
    var tax: Double,
    @Ignore var subtotal: Double
) {
    constructor(
        name: String,
        createdAt: DateTime,
        unitPrice: Double,
        discount: Double,
        quantity: Int,
        tax: Double
    ) : this(0, name, createdAt, unitPrice, discount, quantity, tax, (quantity * unitPrice) * (1 - discount + tax))
}