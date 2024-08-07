package com.example.foody.feature_recipe.util

import android.content.Context
import android.text.Spanned
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.IntSize
import androidx.core.text.HtmlCompat
import com.example.foody.R

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = ""
    )

    val shimmerColor = listOf(
        Color(0xFFD3D3D3),
        Color(0xFFE0E0E0),
        Color(0xFFF5F5F5)
    )

    background(
        brush = Brush.linearGradient(
            colors = shimmerColor,
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}


@Composable
fun String.rememberHtmlText(): AnnotatedString {
    return remember(this) {
        htmlToAnnotatedString(this)
    }
}

private fun htmlToAnnotatedString(html: String): AnnotatedString {
    val spanned: Spanned = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
    return buildAnnotatedString {
        append(spanned.toString())
    }
}

fun String.formatErrorMessage(context: Context): String {
    return when {
        this.contains(REQUEST_LIMIT_CODE) or this.contains(REQUEST_RATE_LIMIT_CODE) -> context.getString(
            R.string.request_limit_reached_please_try_again_later
        )

        this.contains(
            TIMEOUT,
            true
        ) -> context.getString(R.string.request_timed_out_please_check_your_connection_and_try_again)

        else -> context.getString(R.string.an_unexpected_error_occurred_please_try_again)
    }
}

private const val REQUEST_LIMIT_CODE = "402"
private const val TIMEOUT = "timeout"
private const val REQUEST_RATE_LIMIT_CODE = "429"

