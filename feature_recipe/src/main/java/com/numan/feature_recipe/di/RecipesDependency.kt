package com.numan.feature_recipe.di

import com.numan.feature_recipe.data.remote.RecipeApiInterface
import com.numan.feature_recipe.data.repository.RecipesRepositoryImpl
import com.numan.feature_recipe.data.util.NetworkConstants
import com.numan.feature_recipe.domain.repository.RecipesRepository
import com.numan.feature_recipe.domain.usecase.GetRecipeUseCase
import com.numan.feature_recipe.domain.usecase.RecipeSortingUseCase
import com.numan.feature_recipe.presentation.HomeScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * I am using koin here. So we can move to KMM easily later.
 */
val recipeDependency = module {

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
        get<Retrofit>().create(RecipeApiInterface::class.java)
    }

    single<RecipesRepository> {
        RecipesRepositoryImpl(get<RecipeApiInterface>())
    }

    singleOf(::GetRecipeUseCase)

    singleOf(::RecipeSortingUseCase)

    viewModelOf(::HomeScreenViewModel)

}