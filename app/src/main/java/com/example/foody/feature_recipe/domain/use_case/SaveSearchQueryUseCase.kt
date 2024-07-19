package com.example.foody.feature_recipe.domain.use_case

import com.example.foody.feature_recipe.domain.repo.SearchRepository
import javax.inject.Inject

class SaveSearchQueryUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(query: String) {
        searchRepository.saveSearchQuery(query = query)
    }
}