package com.example.mocktest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mocktest.R
import com.example.mocktest.databinding.FragmentDetailBinding
import com.example.mocktest.data.entity.MealFirebase
import com.example.mocktest.ui.MealViewModel
import com.squareup.picasso.Picasso


class FragmentDetail : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args: FragmentDetailArgs by navArgs()
    private val viewModel: MealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeToMenu()
        changToSearch()

        binding = FragmentDetailBinding.bind(view)
        val meal = args.MealFirebase

        val adapter = DetailAdapter(meal)
        adapter.setDetail()
        binding.rvDetail.adapter = adapter

        binding.tvHowToMake.text = meal.strInstructions
        binding.tvTenFoodDetail.text = meal.strMeal
        Picasso.get().load(meal.strMealThumb?.toUri()).into(binding.imgFoodDetail)

       saveDetail(meal)
    }

    private fun setLikeDetail(mealFirebase: MealFirebase){
        if (mealFirebase.Like){
            binding.imgLikeDetail.visibility = View.GONE
            binding.imgLikeRedDetail.visibility = View.VISIBLE
        } else{
            binding.imgLikeDetail.visibility = View.VISIBLE
            binding.imgLikeRedDetail.visibility = View.GONE
        }
    }

    private fun saveDetail(mealFirebase: MealFirebase) {
        binding.imgLikeDetail.setOnClickListener {
            binding.imgLikeDetail.visibility = View.GONE
            binding.imgLikeRedDetail.visibility = View.VISIBLE
            viewModel.saveMeal(mealFirebase)
        }
        binding.imgLikeRedDetail.setOnClickListener {
            binding.imgLikeDetail.visibility = View.VISIBLE
            binding.imgLikeRedDetail.visibility = View.GONE
            viewModel.deleteDataSave(mealFirebase)
        }

        setLikeDetail(mealFirebase)
    }

    private fun changToSearch() {
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_detail_to_fragment_search)
        }
    }

    private fun changeToMenu() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_detail_to_fragment_menu)
        }
    }

}