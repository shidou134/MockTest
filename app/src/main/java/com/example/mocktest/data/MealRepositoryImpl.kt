package com.example.mocktest.data

import com.example.mocktest.data.entity.MealFirebase
import com.example.mocktest.data.entity.Meals
import com.example.mocktest.map.Mapper.toMealFirebase
import retrofit2.Call
import retrofit2.Response

class MealRepositoryImpl : MealRepository {
    override fun getListMeal(callback: (List<MealFirebase>) -> Unit) {
        RetrofitBuider.createApiService().getListMeal("b")
            .enqueue(object : retrofit2.Callback<Meals> {
                override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                    callback.invoke(
                        response.body()?.meals?.filterNotNull()
                            ?.map { meal -> meal.toMealFirebase() } ?: listOf())

                }

                override fun onFailure(call: Call<Meals>, t: Throwable) {

                }

            })
    }

    override fun searchListMeal(callback: (List<MealFirebase>) -> Unit, search: String) {
        RetrofitBuider.createApiService().searchListMeal(search)
            .enqueue(object : retrofit2.Callback<Meals> {
                override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                    callback.invoke(
                        response.body()?.meals?.filterNotNull()
                            ?.map { meal -> meal.toMealFirebase() } ?: listOf()
                    )
                }

                override fun onFailure(call: Call<Meals>, t: Throwable) {

                }

            })

    }

    override fun getRandomMeal(callback: (MealFirebase?) -> Unit) {
        RetrofitBuider.createApiService().getRandomMeal()
            .enqueue((object : retrofit2.Callback<Meals> {
                override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                    callback.invoke(
                        response.body()?.meals?.filterNotNull()?.map { meal ->
                            meal.toMealFirebase()
                        }?.randomOrNull()
                    )
                }

                override fun onFailure(call: Call<Meals>, t: Throwable) {

                }

            }))
    }

}