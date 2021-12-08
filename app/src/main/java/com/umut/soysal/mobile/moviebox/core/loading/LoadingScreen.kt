package com.umut.soysal.mobile.moviebox.core.loading

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.umut.soysal.mobile.moviebox.components.LoadingAnimation
import com.umut.soysal.mobile.moviebox.core.ui.theme.MovieBoxTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                LoadingAnimation(modifier = Modifier.size(75.dp))
                Spacer(modifier = Modifier.size(16.dp))
                Text("Loading...")
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoadingScreenPreview() {
    MovieBoxTheme {
        LoadingScreen()
    }
}