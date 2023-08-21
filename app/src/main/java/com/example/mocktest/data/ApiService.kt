package com.example.mocktest.data

import com.example.mocktest.data.entity.Meals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {

    @GET("search.php")
    fun getListMeal(@Query("f") query: String): Call<Meals>
    @GET("search.php")
    fun searchListMeal(@Query("s") query: String): Call<Meals>
    @GET("random.php")
    fun getRandomMeal():Call<Meals>

}