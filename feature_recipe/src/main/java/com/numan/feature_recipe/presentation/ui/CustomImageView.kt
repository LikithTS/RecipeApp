package com.numan.feature_recipe.presentation.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

/**
 * Common view to render imageview inside the cardview by passing corner radius or leave default to show in detail view.
 */
@Composable
fun CustomImageView(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    cornerRadius: Dp = 0.dp,
    placeholder: Painter? = null,
    error: Painter? = null
) {
    val context = LocalContext.current

    val finalImageUrl = imageUrl.removeSuffix("?").let {
        if (it.startsWith("https")) it else "https:$it"
    }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(finalImageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier
            .clip(RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius)),
        contentScale = contentScale,
        placeholder = placeholder,
        error = error
    )
}
