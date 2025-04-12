package com.ys.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ys.core.designsystem.theme.ArticlesTheme

@Composable
fun BoxScope.ArticlesCircularProgress() {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = ArticlesTheme.colors.primary,
    )
}

@Preview
@Composable
private fun ArticlesCircularProgressPreview() {
    ArticlesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ArticlesCircularProgress()
        }
    }
}
