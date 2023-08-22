package com.example.mocktest.data.favoriterepository

import android.util.Log
import com.example.mocktest.data.entity.MealFirebase
import com.google.firebase.database.*

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
        ds = mutableListOf<MealFirebase>() as ArrayList<MealFirebase>
        dbref = FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        Log.i("123312", snapshot.children.toString())
                        val data = snap.child("Meal").getValue(MealFirebase::class.java)
                        Log.d(
                            "43523",
                            snap.child("Meal").getValue(MealFirebase::class.java).toString()
                        )
                        ds.add(data!!)
                    }
                    callback.invoke(ds.toList())
                } else {
                    callback.invoke(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
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