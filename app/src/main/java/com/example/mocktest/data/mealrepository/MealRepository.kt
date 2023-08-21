package com.example.mocktest.data.mealrepository

import com.example.mocktest.data.entity.MealFirebase


interface MealRepository {
    fun getListMeal(callback: (List<MealFirebase>) -> Unit)
    fun searchListMeal(callback: (List<MealFirebase>) -> Unit, search: String)
    fun getRandomMeal(callback: (MealFirebase?) -> Unit)
}