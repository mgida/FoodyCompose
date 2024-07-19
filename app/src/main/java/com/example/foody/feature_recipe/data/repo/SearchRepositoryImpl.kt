package com.example.foody.feature_recipe.data.repo

import android.content.Context
import com.example.foody.feature_recipe.data.local.SearchPreferences
import com.example.foody.feature_recipe.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val context: Context) : SearchRepository {
    override fun getRecentSearches(): Flow<Set<String>> =
        SearchPreferences.getRecentSearches(context)

    override suspend fun saveSearchQuery(query: String) {
        SearchPreferences.saveSearchQuery(context = context, query = query)
    }
}