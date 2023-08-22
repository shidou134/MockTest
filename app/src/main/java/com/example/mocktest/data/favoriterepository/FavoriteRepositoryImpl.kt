package com.example.mocktest.data.favoriterepository

import android.util.Log
import com.example.mocktest.data.entity.MealFirebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FavoriteRepositoryImpl : FavoriteRepository {
    private lateinit var dbref: DatabaseReference
    private lateinit var ds: ArrayList<MealFirebase>
    override fun saveMeal(mealFirebase: MealFirebase) {
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

    }

    override fun deleteDataFavorite(mealFirebase: MealFirebase) {
        ds = mutableListOf<MealFirebase>() as ArrayList<MealFirebase>
        dbref =
            FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
                .child(mealFirebase.idMeal!!)
        mealFirebase.Like = false
        dbref.removeValue().addOnSuccessListener {
            Log.d("remove", dbref.removeValue().toString())
        }.addOnFailureListener {

        }
    }
}