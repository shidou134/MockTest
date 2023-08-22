package com.example.mocktest.data.favoriterepo

import com.example.mocktest.data.entity.MealFirebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FavoriteRepositoryImpl:FavoriteRepository {
    private lateinit var dbref: DatabaseReference
    override fun saveMeal( mealFirebase: MealFirebase) {
        dbref = FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
        mealFirebase.Like = true
        val value = HashMap<String, MealFirebase>()
        value["Meal"] = mealFirebase
        val idMeal = mealFirebase.idMeal
        if (idMeal != null) {
            dbref.child(idMeal).setValue(value)
        }
    }

    override fun getDataFavorite(callback: (List<MealFirebase>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun deleteDataFavorite(callback: (List<MealFirebase>) -> Unit) {
        TODO("Not yet implemented")
    }
}