package com.example.roomdbflow

import android.app.Application
import androidx.room.Room

class ProductProvider : Application() {
    companion object {
        lateinit  var database: DatabaseContext
    }


    override fun onCreate() {
        super.onCreate()
//        ProductProvider.database = Room.databaseBuilder(this, ProductDao::class, )
    }

}