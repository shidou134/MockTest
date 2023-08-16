package com.example.mocktest.viewmodel

import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mocktest.map.Mapper.mapToSaved
import com.example.mocktest.model.Meals
import com.example.mocktest.model.Saved
import com.example.mocktest.repositories.RetrofitBuider
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class MealViewModel : ViewModel() {

    private lateinit var dbref: DatabaseReference
    private lateinit var ds: ArrayList<Saved>
    private val _savedLiveData: MutableLiveData<List<Saved?>> = MutableLiveData()

    val savedLiveData: LiveData<List<Saved?>>
        get() = _savedLiveData

    private val _mealsLiveData: MutableLiveData<List<Saved?>> = MutableLiveData()

    val mealsLiveData: LiveData<List<Saved?>>
        get() = _mealsLiveData

    init {
        getMeals()
    }

    fun getMeals() {
        viewModelScope.launch {
            RetrofitBuider.createApiService().getListMeal("b")
                .enqueue(object : retrofit2.Callback<Meals> {
                    override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                        _mealsLiveData.postValue(response.body()?.meals?.map { meal -> meal?.mapToSaved() }
                            ?: emptyList())
                    }

                    override fun onFailure(call: Call<Meals>, t: Throwable) {

                    }

                })
        }
    }

    private val _searchLiveData: MutableLiveData<List<Saved?>> = MutableLiveData()

    val searchLiveData: LiveData<List<Saved?>>
        get() = _searchLiveData

    fun searchMeal(search : String) {
        viewModelScope.launch {
            RetrofitBuider.createApiService().searchListMeal(search)
                .enqueue(object : retrofit2.Callback<Meals> {
                    override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                        _searchLiveData.postValue(response.body()?.meals?.map { meal -> meal?.mapToSaved() }
                            ?: emptyList())
                    }

                    override fun onFailure(call: Call<Meals>, t: Throwable) {

                    }

                })
        }
    }

    private val _mealsRandomLiveData: MutableLiveData<Saved> = MutableLiveData()

    val mealRandomLiveData: LiveData<Saved>
        get() = _mealsRandomLiveData

    fun getRandomMeal() {
        viewModelScope.launch {
            RetrofitBuider.createApiService().getRandomMeal()
                .enqueue(object : retrofit2.Callback<Meals> {
                    override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                        _mealsRandomLiveData.postValue(
                            response.body()?.meals?.get(0)?.mapToSaved()
                        )
                    }

                    override fun onFailure(call: Call<Meals>, t: Throwable) {

                    }

                })
        }
    }

    fun saveMeal(saved: Saved) {
        val db = FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
        viewModelScope.launch {
            saved.Like = true
            val value = HashMap<String, Saved>()
            value["Meal"] = saved
            val idMeal = saved.idMeal
            if (idMeal != null) {
                db.child(idMeal).setValue(value)
            }
        }
    }

    fun getDataSave() {
        ds = mutableListOf<Saved>() as ArrayList<Saved>
        dbref = FirebaseDatabase.getInstance().getReference("listFavoriteMeals")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        Log.i("123312", snapshot.children.toString())
                        val data = snap.child("Meal").getValue(Saved::class.java)
                        Log.d("43523", snap.child("Meal").getValue(Saved::class.java).toString())
                        ds.add(data!!)
                    }
                    _savedLiveData.postValue(ds.toList())
                } else {
                    _savedLiveData.postValue(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun deleteDataSave(saved: Saved) {
        ds = mutableListOf<Saved>() as ArrayList<Saved>
        dbref =
            FirebaseDatabase.getInstance().getReference("listFavoriteMeals").child(saved.idMeal!!)
        saved.Like = false
        dbref.removeValue().addOnSuccessListener {
            Log.d("remove", dbref.removeValue().toString())
        }.addOnFailureListener {

        }
    }
}


