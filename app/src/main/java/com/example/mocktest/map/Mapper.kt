package com.example.mocktest.map

import com.example.mocktest.model.Meal
import com.example.mocktest.model.Saved

object Mapper {
    fun Meal.mapToSaved(): Saved {
        return Saved(
            idMeal = this.idMeal,
            strArea = this.strArea,
            strCategory = this.strCategory,
            strImageSource = this.strImageSource,
            strIngredient1 = this.strIngredient1,
            strIngredient10 = this.strIngredient10,
            strIngredient11 = this.strIngredient11,
            strIngredient12 = this.strIngredient12,
            strIngredient13 = this.strIngredient13,
            strIngredient14 = this.strIngredient14,
            strIngredient15 = this.strIngredient15,
            strIngredient16 = this.strIngredient16,
            strIngredient17 = this.strIngredient17,
            strIngredient18 = this.strIngredient18,
            strIngredient19 = this.strIngredient19,
            strIngredient2 = this.strIngredient2,
            strIngredient20 = this.strIngredient20,
            strIngredient3 = this.strIngredient3,
            strIngredient4 = this.strIngredient4,
            strIngredient5 = this.strIngredient5,
            strIngredient6 = this.strIngredient6,
            strIngredient7 = this.strIngredient7,
            strIngredient8 = this.strIngredient8,
            strIngredient9 = this.strIngredient9,
            strInstructions = this.strInstructions,
            strMeal = this.strMeal,
            strMealThumb = this.strMealThumb,
            strMeasure1 = this.strMeasure1,
            strMeasure10 = this.strMeasure10,
            strMeasure11 = this.strMeasure11,
            strMeasure12 = this.strMeasure12,
            strMeasure13 = this.strMeasure13,
            strMeasure14 = this.strMeasure14,
            strMeasure15 = this.strMeasure15,
            strMeasure16 = this.strMeasure16,
            strMeasure17 = this.strMeasure17,
            strMeasure18 = this.strMeasure18,
            strMeasure19 = this.strMeasure19,
            strMeasure2 = this.strMeasure2,
            strMeasure20 = this.strMeasure20,
            strMeasure3 = this.strMeasure3,
            strMeasure4 = this.strMeasure4,
            strMeasure5 = this.strMeasure5,
            strMeasure6 = this.strMeasure6,
            strMeasure7 = this.strMeasure7,
            strMeasure8 = this.strMeasure8,
            strMeasure9 = this.strMeasure9
        )
    }


}