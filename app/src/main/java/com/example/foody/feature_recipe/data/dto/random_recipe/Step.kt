package com.example.foody.feature_recipe.data.dto.random_recipe

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)