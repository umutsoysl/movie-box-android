package com.umut.soysal.mobile.moviebox.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.umut.soysal.mobile.moviebox.R
import com.umut.soysal.mobile.moviebox.core.ui.theme.MovieBoxTheme

@Composable
fun LoadingAnimation(modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = 0.dp) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_lottie))

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingAnimationPreview() {
    MovieBoxTheme {
        LoadingAnimation(modifier = Modifier.size(75.dp))
    }
}