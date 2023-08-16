package com.example.mocktest.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mocktest.R
import com.example.mocktest.adapter.MenuAdapter
import com.example.mocktest.databinding.FragmentSearchBinding
import com.example.mocktest.model.Saved
import com.example.mocktest.viewmodel.MealViewModel
import com.google.android.material.search.SearchView
import java.util.*
import kotlin.collections.ArrayList

class FragmentSearch : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var list = ArrayList<Saved>()
    private val viewModel: MealViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        changToMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        var adapter = MenuAdapter(emptyList())
        binding.rvSearch.adapter = adapter


        viewModel.mealsLiveData.observe(this) {
            adapter.updateData(it.filterNotNull())
        }

        viewModel.searchLiveData.observe(this) {
            adapter.updateData(it.filterNotNull())
        }

        adapter.onItemClick = { num, saved ->
            when (num) {
                0 -> {
                    val action = FragmentSearchDirections.actionFragmentSearchToFragmentDetail(saved)
                    findNavController().navigate(action)
                }
                1 -> {
                    viewModel.saveMeal(saved)
                }
                2 -> {
                    viewModel.deleteDataSave(saved)
                }
            }

        }

        binding.svSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchMeal(newText)
                } else {
                    adapter.updateData(emptyList())
                }
                return true
            }

        })

    }

    private fun changToMenu() {
        binding.btnBackSearch.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_search_to_fragment_menu)
        }
    }

}