package com.example.mocktest.data

import com.example.mocktest.data.entity.MealFirebase
import com.example.mocktest.data.entity.Meals

interface MealRepository {
    fun getListMeal(callback: (List<MealFirebase>) -> Unit)
    fun searchListMeal(callback: (List<MealFirebase>) -> Unit, search: String)
    fun getRandomMeal(callback: (MealFirebase?) -> Unit)
}