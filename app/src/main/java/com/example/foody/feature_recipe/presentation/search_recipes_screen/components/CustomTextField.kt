package com.example.foody.feature_recipe.presentation.search_recipes_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foody.feature_recipe.util.ThemePreviews
import com.example.foody.ui.theme.FoodyTheme

@Composable
fun CustomBasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    textStyle: TextStyle = LocalTextStyle.current,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentPadding: PaddingValues = PaddingValues(16.dp)
) {
    Box(modifier = modifier.background(color = backgroundColor, shape = RoundedCornerShape(8.dp))) {
        if (value.isEmpty()) {
            Text(
                text = hint,
                style = textStyle.copy(color = Color.Gray),
                modifier = Modifier.padding(contentPadding)
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()
        )
    }
}

@ThemePreviews
@Composable
fun CustomBasicTextFieldPreview() {

    FoodyTheme {
        CustomBasicTextField(
            value = "",
            onValueChange = { },
            hint = "Search recipes..",
            textStyle = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface),
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.padding(16.dp)
        )
    }
}

