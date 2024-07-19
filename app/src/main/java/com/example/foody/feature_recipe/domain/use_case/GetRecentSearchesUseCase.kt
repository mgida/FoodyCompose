package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.repo.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentSearchesUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    operator fun invoke(): Flow<Set<String>> {
        return searchRepository.getRecentSearches()
    }
}