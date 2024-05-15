package com.example.foody.feature_recipe.domain.model.random_recipe

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val number: Int,
    val step: String
)