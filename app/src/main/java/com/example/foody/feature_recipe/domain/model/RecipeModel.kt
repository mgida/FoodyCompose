package com.example.foody.feature_recipe.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeModel(
    @PrimaryKey
    val id: Int,
    val image: String?,
    val title: String?,
    val summary: String?,
    val sourceUrl: String?,
    val isFav: Boolean = false
)
