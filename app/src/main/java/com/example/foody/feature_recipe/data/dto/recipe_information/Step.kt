package com.example.foody.feature_recipe.data.dto.recipe_information

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)