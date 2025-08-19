package com.numan.feature_detail_recipe.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.numan.feature_detail_recipe.R
import com.numan.feature_detail_recipe.domain.model.DetailRecipeUiModel

/**
 * As the name suggest it is used to display details of recipe
 */
@Composable
fun DetailRecipeContentView(
    recipeData: DetailRecipeUiModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //We should not use items here. So item to display single item with scrolling functionality
        item {
            ElevatedCard(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box {
                    CustomImageView(
                        imageUrl = recipeData.image,
                        contentDescription = recipeData.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                        cornerRadius = 16.dp
                    )

                    // Name and rating on image view
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    0f to Color.Transparent,
                                    0.6f to Color.Transparent,
                                    1f to MaterialTheme.colorScheme.scrim.copy(alpha = 0.35f)
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = recipeData.name,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(Modifier.height(6.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = stringResource(R.string.rating),
                                tint = colorResource(R.color.secondary_blue)
                            )

                            Spacer(Modifier.width(6.dp))

                            Text(
                                text = "${recipeData.rating} (${recipeData.reviewCount})",
                                style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
                            )
                        }
                    }
                }
            }
        }

        // Quick guide on recipes
        item {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FlexboxView(stringResource(R.string.serving), "${recipeData.servings}")
                FlexboxView(stringResource(R.string.cuisine), recipeData.cuisine)
                FlexboxView(stringResource(R.string.difficulty), recipeData.difficulty)
                FlexboxView(stringResource(R.string.preparation_time), "${recipeData.prepTimeMinutes} min")
                FlexboxView(stringResource(R.string.cook_time), "${recipeData.cookTimeMinutes} min")
                FlexboxView(stringResource(R.string.calories), "${recipeData.caloriesPerServing}")
            }
        }

        // Ingredients
        item {
            SectionHeader(text = stringResource(R.string.ingredients))
        }

        items(recipeData.ingredients, key = { it }) { ingredient ->
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("-> ", style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = ingredient,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Instructions
        item {
            SectionHeader(text = stringResource(R.string.instruction))
        }
        itemsIndexed(recipeData.instructions, key = { index, _ -> index }) { index, step ->
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${index + 1}. ",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = step,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Column {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(6.dp))
        HorizontalDivider()
    }
}
