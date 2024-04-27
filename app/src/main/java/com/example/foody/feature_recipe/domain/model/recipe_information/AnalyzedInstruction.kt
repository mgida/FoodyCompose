package com.example.foody.feature_recipe.domain.model.recipe_information

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)