package com.numan.feature_detail_recipe.presentation.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.numan.feature_detail_recipe.R
import com.numan.feature_detail_recipe.domain.util.NetworkErrorApiResponse

/**
 * To keep things simple. I am now showing toast for error. Based on our use cases we can change few things here.
 * Like we can show error dialog, navigate to error page or show some error in page.
 * Using abstract error instead of specific error now.
 */
@Composable
fun HandleErrorView(networkError: NetworkErrorApiResponse) {

    val context = LocalContext.current

    when (networkError) {
        NetworkErrorApiResponse.NOT_FOUND -> {
            Toast.makeText(context, context.getText(R.string.api_not_found), Toast.LENGTH_SHORT)
                .show()
        }

        NetworkErrorApiResponse.UNAUTHORISED -> {
            //You can handle in 2 different ways.
            //1. If you have refresh token, then do a silent api call to get new token and do not show this error.
            //2. If you don't have refresh token, then show error message and move to login page.
            Toast.makeText(context, context.getText(R.string.un_authorised), Toast.LENGTH_SHORT)
                .show()

        }

        NetworkErrorApiResponse.FAILURE -> {
            Toast.makeText(context, context.getText(R.string.failure), Toast.LENGTH_SHORT)
                .show()
        }

        is NetworkErrorApiResponse.GenericException -> {
            Toast.makeText(context, networkError.errorMessage, Toast.LENGTH_SHORT).show()
        }
       // We can handle this as a future enhancement.
        NetworkErrorApiResponse.BAD_REQUEST -> TODO()
        NetworkErrorApiResponse.EMPTY_RESPONSE -> TODO()
        NetworkErrorApiResponse.REQUEST_TIMEOUT -> TODO()
    }
}