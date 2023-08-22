package com.example.mocktest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mocktest.data.mealrepository.MealRepository
import com.example.mocktest.data.mealrepository.MealRepositoryImpl
import com.example.mocktest.data.entity.MealFirebase
import com.example.mocktest.data.favoriterepo.FavoriteRepository
import com.example.mocktest.data.favoriterepo.FavoriteRepositoryImpl
import com.google.firebase.database.*
import kotlinx.coroutines.launch

class MealViewModel(
    private val repository: MealRepository = MealRepositoryImpl(),
    private val repositoryFavorite: FavoriteRepository = FavoriteRepositoryImpl()
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
        dbref = FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
        viewModelScope.launch {
            repositoryFavorite.saveMeal(mealFirebase = mealFirebase)
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


