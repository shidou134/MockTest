package com.example.mocktest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mocktest.data.MealRepository
import com.example.mocktest.data.MealRepositoryImpl
import com.example.mocktest.map.Mapper.toMealFirebase
import com.example.mocktest.data.entity.Meals
import com.example.mocktest.data.entity.MealFirebase
import com.example.mocktest.data.RetrofitBuider
import com.google.firebase.database.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MealViewModel(
    private val repository: MealRepository = MealRepositoryImpl()
) : ViewModel() {

    private lateinit var dbref: DatabaseReference
    private lateinit var ds: ArrayList<MealFirebase>
    private val _mealFirebaseLiveData: MutableLiveData<List<MealFirebase?>> = MutableLiveData()

    val mealFirebaseLiveData: LiveData<List<MealFirebase?>>
        get() = _mealFirebaseLiveData

    private val _mealsLiveData: MutableLiveData<List<MealFirebase?>> = MutableLiveData()

    val mealsLiveData: LiveData<List<MealFirebase?>>
        get() = _mealsLiveData

    init {
        getMeals()
    }

    fun getMeals() {
        viewModelScope.launch {
            repository.getListMeal {
                _mealsLiveData.postValue(it)
            }
        }
    }

    private val _searchLiveData: MutableLiveData<List<MealFirebase?>> = MutableLiveData()
    val searchLiveData: LiveData<List<MealFirebase?>>
        get() = _searchLiveData

    fun searchMeal(search: String) {
        viewModelScope.launch {
            repository.searchListMeal(
                callback = {
                    _mealsLiveData.postValue(it)
                },
                search = search
            )
        }
    }

    private val _mealsRandomLiveData: MutableLiveData<MealFirebase> = MutableLiveData()

    val mealRandomLiveData: LiveData<MealFirebase>
        get() = _mealsRandomLiveData

    fun getRandomMeal() {
        viewModelScope.launch {
            repository.getRandomMeal {
                _mealsRandomLiveData.postValue(it)
            }
        }
    }

    fun saveMeal(mealFirebase: MealFirebase) {
        val db = FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
        viewModelScope.launch {
            mealFirebase.Like = true
            val value = HashMap<String, MealFirebase>()
            value["Meal"] = mealFirebase
            val idMeal = mealFirebase.idMeal
            if (idMeal != null) {
                db.child(idMeal).setValue(value)
            }
        }
    }

    fun getDataSave() {
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
                    _mealFirebaseLiveData.postValue(ds.toList())
                } else {
                    _mealFirebaseLiveData.postValue(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun deleteDataSave(mealFirebase: MealFirebase) {
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


