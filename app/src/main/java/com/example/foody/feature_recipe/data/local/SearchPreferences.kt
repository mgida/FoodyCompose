package com.example.foody.feature_recipe.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val SEARCH_DATASTORE = "recent_searches"
private const val RECENT_SEARCH_KEY = "recent"

val Context.dataStore by preferencesDataStore(name = SEARCH_DATASTORE)

object SearchPreferences {
    private val RECENT_SEARCHES_KEY = stringSetPreferencesKey(RECENT_SEARCH_KEY)

    fun getRecentSearches(context: Context): Flow<Set<String>> {
        val recentSearches: Flow<Set<String>> = context.dataStore.data
            .map { preferences ->
                preferences[RECENT_SEARCHES_KEY] ?: emptySet()
            }
        return recentSearches
    }

     suspend fun saveSearchQuery(context: Context, query: String) {
        context.dataStore.edit { preferences ->
            val currentSearches = preferences[RECENT_SEARCHES_KEY] ?: emptySet()
            preferences[RECENT_SEARCHES_KEY] = currentSearches + query
        }
    }
}


