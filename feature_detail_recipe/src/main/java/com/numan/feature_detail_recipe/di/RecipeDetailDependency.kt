package com.numan.feature_detail_recipe.di

import com.numan.feature_detail_recipe.data.remote.RecipeDetailAPIInterface
import com.numan.feature_detail_recipe.data.repository.RecipeDetailRepositoryImpl
import com.numan.feature_detail_recipe.data.util.NetworkConstants
import com.numan.feature_detail_recipe.domain.repository.RecipeDetailRepository
import com.numan.feature_detail_recipe.domain.usecase.GetDetailRecipeDataUseCase
import com.numan.feature_detail_recipe.presentation.DetailRecipeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

/**
 * Koin dependency injection
 */
val recipeDetailDependency = module {

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(RecipeDetailAPIInterface::class.java)
    }

    single<RecipeDetailRepository> {
        RecipeDetailRepositoryImpl(get<RecipeDetailAPIInterface>())
    }

    singleOf(::GetDetailRecipeDataUseCase)

    viewModelOf(::DetailRecipeViewModel)
}