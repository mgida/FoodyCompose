package com.example.foody.feature_recipe.domain.model.recipe_information

data class WinePairing(
    val pairedWines: List<Any>,
    val pairingText: String,
    val productMatches: List<Any>
)