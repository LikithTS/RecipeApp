package com.numan.recipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.numan.feature_detail_recipe.presentation.ui.DetailRecipeScreenView
import com.numan.feature_recipe.presentation.ui.RecipeHomeScreen
import com.numan.recipes.ui.theme.RecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.RecipeHomeScreen
                    ) {
                        composable<Route.RecipeHomeScreen> {
                            RecipeHomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                onCardItemClicked = { id->
                                    navController.navigate(Route.DetailRecipeScreen(id))
                                }
                            )
                        }

                        composable<Route.DetailRecipeScreen> { backStackEntry ->
                            val args = backStackEntry.toRoute<Route.DetailRecipeScreen>()
                            val id = args.id

                            DetailRecipeScreenView(
                                modifier = Modifier.padding(innerPadding),
                                recipeId = id,
                                onButtonClick = {
                                    navController.navigate(Route.RecipeHomeScreen) {
                                        popUpTo<Route.DetailRecipeScreen> {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}