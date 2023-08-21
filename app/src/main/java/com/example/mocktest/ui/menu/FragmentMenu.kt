package com.example.mocktest.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mocktest.R
import com.example.mocktest.ui.adapter.MenuAdapter
import com.example.mocktest.databinding.FragmentMenuBinding
import com.example.mocktest.data.entity.MealFirebase
import com.example.mocktest.ui.MealViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class FragmentMenu : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var dbref: DatabaseReference

    private val _viewModel: MealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        dbref = FirebaseDatabase.getInstance().reference.child("meal")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMenuBinding.bind(view)

        val adapter = MenuAdapter(emptyList())
        binding.rvRecentMeals.adapter = adapter
//        _viewModel.getMeals()
        _viewModel.getRandomMeal()

        _viewModel.mealsLiveData.observe(this) {
            adapter.updateData(it.filterNotNull())
        }

        adapter.onItemClick = { num, saved ->
            when (num) {
                0 -> {
                    val action = FragmentMenuDirections.actionFragmentMenuToFragmentDetail(saved)
                    findNavController().navigate(action)
                }
                1 -> {
                    _viewModel.saveMeal(saved)
                }
                2 -> {
                    _viewModel.deleteDataSave(saved)
                }
            }
        }

        changeToSearch()
        changToListMeal()

        _viewModel.mealRandomLiveData.observe(this) {
            binding.tvTenMonAn.text = it.strMeal
            Picasso.get().load(it.strMealThumb?.toUri()).into(binding.imgFood)
            navToDetail(it)
        }
    }

    private fun changToListMeal() {
        binding.btnSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_fragment_list_meals)
        }
    }

    private fun navToDetail(mealFirebase: MealFirebase) {
        binding.btnMakeIt.setOnClickListener {
            val action = FragmentMenuDirections.actionFragmentMenuToFragmentDetail(mealFirebase)
            findNavController().navigate(action)
        }
    }

    private fun changeToSearch() {
        binding.search.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_menu_to_fragment_search)
        }
    }
}