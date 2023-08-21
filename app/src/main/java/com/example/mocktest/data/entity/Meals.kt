package com.example.mocktest.data.entity


import com.example.mocktest.data.entity.Meal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meals(
    @SerialName("meals")
    val meals: List<Meal?>?
)