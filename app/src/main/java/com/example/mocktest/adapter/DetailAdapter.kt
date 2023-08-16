package com.example.mocktest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mocktest.databinding.ListItemDetailBinding
import com.example.mocktest.model.Ingredient
import com.example.mocktest.model.Saved

class DetailAdapter(private val saved: Saved) :
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
        if (!saved.strIngredient1.isNullOrEmpty()) list.add(Ingredient(saved.strIngredient1, saved.strMeasure1 ?: "__"))
       
        if(!saved.strIngredient2.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient2, saved.strMeasure2 ?: "__"))
        }
        if(!saved.strIngredient3.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient3, saved.strMeasure3 ?: "__"))
        }
        if(!saved.strIngredient4.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient4, saved.strMeasure4 ?: "__"))
        }
        if(!saved.strIngredient5.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient5, saved.strMeasure5 ?: "__"))
        }
        if(!saved.strIngredient6.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient6, saved.strMeasure6 ?: "__"))
        }
        if(!saved.strIngredient7.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient7, saved.strMeasure7 ?: "__"))
        }
        if(!saved.strIngredient8.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient8, saved.strMeasure8 ?: "__"))
        }
        if(!saved.strIngredient9.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient9, saved.strMeasure9 ?: "__"))
        }
        if(!saved.strIngredient10.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient10, saved.strMeasure10 ?: "__"))
        }
        if(!saved.strIngredient11.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient11, saved.strMeasure11 ?: "__"))
        }
        if(!saved.strIngredient12.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient12, saved.strMeasure12 ?: "__"))
        }
        if(!saved.strIngredient13.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient13, saved.strMeasure13 ?: "__"))
        }
        if(!saved.strIngredient14.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient14, saved.strMeasure14 ?: "__"))
        }
        if(!saved.strIngredient15.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient15, saved.strMeasure15 ?: "__"))
        }
        if(!saved.strIngredient16.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient16, saved.strMeasure16 ?: "__"))
        }
        if(!saved.strIngredient17.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient17, saved.strMeasure17 ?: "__"))
        }
        if(!saved.strIngredient18.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient18, saved.strMeasure18 ?: "__"))
        }
        if(!saved.strIngredient19.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient19, saved.strMeasure19 ?: "__"))
        }
        if(!saved.strIngredient20.isNullOrEmpty()) {
            list.add(Ingredient(saved.strIngredient20, saved.strMeasure20 ?: "__"))
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