package com.example.foody.feature_recipe.domain.model.search_recipes

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SearchRecipesModel(
    @PrimaryKey
    val id: Int,
    val image: String?,
    val title: String?,
    val summary: String?,
    val isFav : Boolean = false
)
