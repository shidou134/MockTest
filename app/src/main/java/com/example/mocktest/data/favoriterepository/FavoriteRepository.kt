package com.example.mocktest.data.favoriterepository

import com.example.mocktest.data.entity.MealFirebase

interface FavoriteRepository {

    fun saveMeal(mealFirebase: MealFirebase)
    fun getDataFavorite(callback: (List<MealFirebase>) -> Unit)
    fun deleteDataFavorite(mealFirebase: MealFirebase)
}