package com.umut.soysal.mobile.moviebox.feature.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.umut.soysal.mobile.moviebox.core.navigation.Page


@Composable
fun SplashScreen(
    navController: NavController? = null
) {
    var text by rememberSaveable { mutableStateOf("")}
    Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column (modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(onClick = { navController?.navigate(Page.MovieListScreen.route)}){
                        Text(text = "Movie List")
                    }
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun previewSplash() {
    SplashScreen()
}