package com.numan.feature_recipe.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.numan.feature_recipe.R
import com.numan.feature_recipe.domain.model.RecipeUiModel

/**
 * Portrait view for phones or smaller screen size.
 */
@Composable
fun RecipePortraitCardView(
    recipeData: RecipeUiModel
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 6.dp, start = 8.dp, end = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            CustomImageView(
                imageUrl = recipeData.image,
                contentDescription = recipeData.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop,
                cornerRadius = 16.dp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                Text(
                    text = recipeData.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(8.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TableView(
                            key = stringResource(R.string.serving),
                            value = recipeData.serving.toString()
                        )
                        TableView(
                            key = stringResource(R.string.cuisine),
                            value = recipeData.cuisine
                        )
                        TableView(
                            key = stringResource(R.string.difficulty),
                            value = recipeData.difficulty
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(R.string.rating)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${recipeData.rating} (${recipeData.reviewCount})",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}