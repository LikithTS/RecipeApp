package com.numan.feature_detail_recipe.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.numan.feature_detail_recipe.R

/**
 * Header to display name and back button. This can be re-used in any project.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRecipeHeaderView(onBackButtonClicked: () -> Unit, recipeName: String) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.primary_blue),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        title = {
            Text(
                recipeName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClicked) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = stringResource(R.string.back_arrow),
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
    )

}