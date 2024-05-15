package com.example.foody.feature_recipe.domain.mapper

interface IMapper<From, To> {
    fun map(input: From): To
}

