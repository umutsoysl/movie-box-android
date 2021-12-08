package com.umut.soysal.mobile.moviebox.core.error

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ErrorScreen(message: String) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = message)
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewErrorScreen() {
    ErrorScreen(message = "test")
}