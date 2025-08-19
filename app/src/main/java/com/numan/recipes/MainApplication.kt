package com.numan.recipes

import android.app.Application
import com.numan.feature_detail_recipe.di.recipeDetailDependency
import com.numan.feature_recipe.di.recipeDependency
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(recipeDependency)
            modules(recipeDetailDependency)
        }
    }
}