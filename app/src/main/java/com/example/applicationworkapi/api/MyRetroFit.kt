package com.example.applicationworkapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetroFit {
    private val retrofit: Retrofit

    fun productApi(): ProductApi{
        return retrofit.create(ProductApi::class.java)
    }

    companion object {
        private const val BASE_URL = "????"
        var myRetrofit: MyRetroFit? = null

        @get:Synchronized
        val instance: MyRetroFit?
            get() {
                if(myRetrofit == null){
                    myRetrofit = MyRetroFit()
                }
                return myRetrofit
            }
    }

    init{
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }
}