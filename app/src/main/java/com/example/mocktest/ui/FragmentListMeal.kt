package com.example.mocktest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mocktest.R
import com.example.mocktest.adapter.MenuAdapter
import com.example.mocktest.databinding.FragmentListMealsBinding
import com.example.mocktest.viewmodel.MealViewModel

class FragmentListMeal : Fragment() {

    private lateinit var binding: FragmentListMealsBinding

    private val _viewModel: MealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListMealsBinding.bind(view)
        changToSearch()
        _viewModel.getMeals()

        val adapter = MenuAdapter(emptyList())
        binding.rvRecentMeals.adapter = adapter
        _viewModel.mealsLiveData.observe(this){
            adapter.updateData(it.filterNotNull())
        }

        adapter.onItemClick = { num, saved ->
            when(num){
                0 -> {
                    val action = FragmentListMealDirections.actionFragmentListMealsToFragmentDetail(saved)
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

    }

    private fun changToSearch() {
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_list_meals_to_fragment_search)
        }
    }
}

