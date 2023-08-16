package com.example.mocktest.repositories

import com.example.mocktest.data.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuider {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createApiService(): ApiService = retrofit.create(ApiService::class.java)
}