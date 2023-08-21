package com.example.mocktest.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.mocktest.databinding.ListItemsBinding
import com.example.mocktest.data.entity.MealFirebase
import com.squareup.picasso.Picasso

class MenuAdapter(private var list: List<MealFirebase>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    var onItemClick: ((Int, MealFirebase) -> Unit) = { _, _ ->

    }

    class MenuViewHolder(private val binding: ListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealFirebase, itemClick: ((Int, MealFirebase) -> Unit)) {
            if (item.Like){
                binding.imgLike.visibility = View.GONE
                binding.imgLikeRed.visibility = View.VISIBLE
            } else{
                binding.imgLike.visibility = View.VISIBLE
                binding.imgLikeRed.visibility = View.GONE
            }
            binding.homeMadeP.text = item.strMeal
            Picasso.get().load(item.strMealThumb?.toUri()).into(binding.imageView)
            binding.root.setOnClickListener {
                itemClick(0, item)
            }
            binding.imgLike.setOnClickListener {
                binding.imgLike.visibility = View.GONE
                binding.imgLikeRed.visibility = View.VISIBLE
                itemClick(1, item)
            }
            binding.imgLikeRed.setOnClickListener {
                binding.imgLike.visibility = View.VISIBLE
                binding.imgLikeRed.visibility = View.GONE
                itemClick(2, item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(list[position], onItemClick)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<MealFirebase>) {
        list = newData
        notifyDataSetChanged()
    }
}
