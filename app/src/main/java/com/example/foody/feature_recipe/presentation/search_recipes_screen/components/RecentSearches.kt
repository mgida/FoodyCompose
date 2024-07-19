package com.example.foody.feature_recipe.presentation.search_recipes_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foody.R
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.ui.theme.FoodyTheme


@Composable
@OptIn(ExperimentalLayoutApi::class)
fun RecentSearches(
    recentSearches: Set<String>,
    onChipClicked: (recentSearch: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (recentSearches.isNotEmpty()) {
            Text(
                text = stringResource(R.string.recent_searches),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        FlowRow(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),

            ) {
            recentSearches.forEach { recentSearch ->
                FilterChip(
                    modifier = Modifier.padding(start = 6.dp),
                    border = BorderStroke(width = 2.dp, MaterialTheme.colorScheme.onSurface),
                    selected = false,
                    onClick = {
                        onChipClicked.invoke(recentSearch)
                    },
                    label = {
                        Text(
                            text = recentSearch,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    })
            }
        }
    }
}


@ThemePreviews
@Composable
fun RecentSearchesPreview() {
    FoodyTheme {
        RecentSearches(
            recentSearches = mutableSetOf("pizza", "pasta", "meat")
        ) {}
    }
}