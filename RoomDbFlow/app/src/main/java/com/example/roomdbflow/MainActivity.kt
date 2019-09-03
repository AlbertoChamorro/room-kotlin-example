package com.example.roomdbflow

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbflow.models.Product
import com.example.roomdbflow.viewModel.ProductViewModel

import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime

// https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#4
// https://github.com/amitshekhariitbhu/Android-Debug-Database

// types converters room sqlite
// https://medium.com/androiddevelopers/room-time-2b4cf9672b98
// https://www.joda.org/joda-time/

// https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#8

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->

            val size: Int = (productViewModel.products.value?.size ?: 0) + 1
            val model = Product(
                "Product $size",
                DateTime(),
                20.0,
                0.0,
                6,
                1.0
            )
            productViewModel.add(model)

            Snackbar.make(view, "Product added", Snackbar.LENGTH_LONG).show()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ProductListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

        productViewModel.products.observe(this, Observer { data ->
            data?.let { products ->
                Log.d(TAG, products.size.toString())
                adapter.setProducts(products)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG: String = "MainActivity"
    }
}
