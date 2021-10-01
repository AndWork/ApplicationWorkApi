package com.example.applicationworkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationworkapi.api.MyRetroFit
import com.example.applicationworkapi.model.Product
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    lateinit var recycleProducts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleProducts = findViewById(R.id.recycler_products)
        recycleProducts.layoutManager = LinearLayoutManager(this)
    }

    private fun getData(){
        val call: retrofit2.Call<List<Product>> = MyRetroFit.instance?.productApi()?.getProductApi() as retrofit2.Call<List<Product>>
        call.enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onResponse(call: retrofit2.Call<List<Product>>, response: Response<List<Product>>) {
                val adapter = ProductAdapter(this@MainActivity, response.body().toList(List<Product>))
                recycleProducts.adapter = adapter
            }

            override fun onFailure(call: retrofit2.Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

        })

    }
}