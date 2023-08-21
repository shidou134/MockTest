package com.example.mocktest.ui.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mocktest.databinding.ListItemDetailBinding
import com.example.mocktest.model.Ingredient
import com.example.mocktest.data.entity.MealFirebase

class DetailAdapter(private val mealFirebase: MealFirebase) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {
    private val listIngredients = mutableListOf<Ingredient>()

    class DetailViewHolder(private val binding: ListItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun mealDetail(item: Ingredient) {
            binding.tvNguyenLieu.text = item.nguyenLieu
            binding.tvUnit.text = item.Unit
        }
    }



    fun setDetail() {
        val list = mutableListOf<Ingredient>()
        if (!mealFirebase.strIngredient1.isNullOrEmpty()) list.add(Ingredient(mealFirebase.strIngredient1, mealFirebase.strMeasure1 ?: "__"))
       
        if(!mealFirebase.strIngredient2.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient2, mealFirebase.strMeasure2 ?: "__"))
        }
        if(!mealFirebase.strIngredient3.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient3, mealFirebase.strMeasure3 ?: "__"))
        }
        if(!mealFirebase.strIngredient4.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient4, mealFirebase.strMeasure4 ?: "__"))
        }
        if(!mealFirebase.strIngredient5.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient5, mealFirebase.strMeasure5 ?: "__"))
        }
        if(!mealFirebase.strIngredient6.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient6, mealFirebase.strMeasure6 ?: "__"))
        }
        if(!mealFirebase.strIngredient7.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient7, mealFirebase.strMeasure7 ?: "__"))
        }
        if(!mealFirebase.strIngredient8.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient8, mealFirebase.strMeasure8 ?: "__"))
        }
        if(!mealFirebase.strIngredient9.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient9, mealFirebase.strMeasure9 ?: "__"))
        }
        if(!mealFirebase.strIngredient10.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient10, mealFirebase.strMeasure10 ?: "__"))
        }
        if(!mealFirebase.strIngredient11.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient11, mealFirebase.strMeasure11 ?: "__"))
        }
        if(!mealFirebase.strIngredient12.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient12, mealFirebase.strMeasure12 ?: "__"))
        }
        if(!mealFirebase.strIngredient13.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient13, mealFirebase.strMeasure13 ?: "__"))
        }
        if(!mealFirebase.strIngredient14.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient14, mealFirebase.strMeasure14 ?: "__"))
        }
        if(!mealFirebase.strIngredient15.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient15, mealFirebase.strMeasure15 ?: "__"))
        }
        if(!mealFirebase.strIngredient16.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient16, mealFirebase.strMeasure16 ?: "__"))
        }
        if(!mealFirebase.strIngredient17.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient17, mealFirebase.strMeasure17 ?: "__"))
        }
        if(!mealFirebase.strIngredient18.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient18, mealFirebase.strMeasure18 ?: "__"))
        }
        if(!mealFirebase.strIngredient19.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient19, mealFirebase.strMeasure19 ?: "__"))
        }
        if(!mealFirebase.strIngredient20.isNullOrEmpty()) {
            list.add(Ingredient(mealFirebase.strIngredient20, mealFirebase.strMeasure20 ?: "__"))
        }
        listIngredients.clear()
        listIngredients.addAll(list)
        Log.i("Data detail",listIngredients.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding =
            ListItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listIngredients.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.mealDetail(listIngredients[position])
    }

}