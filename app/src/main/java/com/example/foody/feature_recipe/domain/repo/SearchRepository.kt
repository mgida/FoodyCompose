package com.example.foody.feature_recipe.domain.repo

import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getRecentSearches(): Flow<Set<String>>
    suspend fun saveSearchQuery(query: String)
}
