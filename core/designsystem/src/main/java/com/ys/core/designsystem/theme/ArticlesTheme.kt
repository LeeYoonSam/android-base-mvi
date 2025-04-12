@file:OptIn(ExperimentalComposeUiApi::class)

package com.ys.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

private val LocalColors = compositionLocalOf<ArticleColors> {
    error(
        "No colors provided! Make sure to wrap all usages of ArtArticles components in ArticleTheme.",
    )
}

@Composable
fun ArticlesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: ArticleColors = if (darkTheme) {
        ArticleColors.defaultDarkColors()
    } else {
        ArticleColors.defaultLightColors()
    },
    background: ArticlesBackground = ArticlesBackground.defaultBackground(darkTheme),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background,
    ) {
        Box(
            modifier = Modifier
                .background(background.color)
                .semantics { testTagsAsResourceId = true },
        ) {
            content()
        }
    }
}

// 앱의 계층 구조에서 호출 사이트의 현재 위치에서 바로 사용하기 위한 용도
object ArticlesTheme {
    val colors: ArticleColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val background: ArticlesBackground
        @Composable
        @ReadOnlyComposable
        get() = LocalBackgroundTheme.current
}
