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
import com.example.mocktest.databinding.FragmentSavedBinding
import com.example.mocktest.model.Saved
import com.example.mocktest.viewmodel.MealViewModel

class FragmentSaved : Fragment() {

    private lateinit var binding: FragmentSavedBinding
    private val viewModel: MealViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSavedBinding.bind(view)

        viewModel.getDataSave()

        val adapter = MenuAdapter(emptyList())
        binding.rvSaved.adapter = adapter

        viewModel.savedLiveData.observe(this) {
            if (it == null) {
                adapter.updateData(emptyList())
            } else {
                adapter.updateData(it.filterNotNull())
            }
        }

        adapter.onItemClick = { num, saved ->
            when (num) {
                0 -> {
                    val action = FragmentSavedDirections.actionFragmentSavedToFragmentDetail(saved)
                    findNavController().navigate(action)
                }
                1 -> {

                }
                2 -> {
                    viewModel.deleteDataSave(saved)
                }
            }
        }
        changeToSearch()
    }

    private fun changeToSearch() {
        binding.search.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_saved_to_fragment_search)
        }
    }

}