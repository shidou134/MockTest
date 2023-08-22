package com.example.mocktest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mocktest.data.mealrepository.MealRepository
import com.example.mocktest.data.mealrepository.MealRepositoryImpl
import com.example.mocktest.data.entity.MealFirebase
import com.example.mocktest.data.favoriterepository.FavoriteRepository
import com.example.mocktest.data.favoriterepository.FavoriteRepositoryImpl
import kotlinx.coroutines.launch

class MealViewModel(
    private val repository: MealRepository = MealRepositoryImpl(),
    private val repositoryFavorite: FavoriteRepository = FavoriteRepositoryImpl()
) : ViewModel() {


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
        viewModelScope.launch {
            repositoryFavorite.saveMeal(mealFirebase = mealFirebase)
        }
    }

    fun getDataSave() {
//        ds = mutableListOf<MealFirebase>() as ArrayList<MealFirebase>
//        dbref = FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
//        dbref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                ds.clear()
//                if (snapshot.exists()) {
//                    for (snap in snapshot.children) {
//                        Log.i("123312", snapshot.children.toString())
//                        val data = snap.child("Meal").getValue(MealFirebase::class.java)
//                        Log.d(
//                            "43523",
//                            snap.child("Meal").getValue(MealFirebase::class.java).toString()
//                        )
//                        ds.add(data!!)
//                    }
//                    _mealFirebaseLiveData.postValue(ds.toList())
//                } else {
//                    _mealFirebaseLiveData.postValue(emptyList())
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
        repositoryFavorite.getDataFavorite {
            _mealFirebaseLiveData.postValue(it)
        }

    }

    fun deleteDataSave(mealFirebase: MealFirebase) {
        repositoryFavorite.deleteDataFavorite(mealFirebase = mealFirebase)
    }
}


