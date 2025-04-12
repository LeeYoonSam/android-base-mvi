package com.ys.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ys.core.designsystem.R

@Immutable
data class ArticlesBackground(
  val color: Color = Color.Unspecified,
  val tonalElevation: Dp = Dp.Unspecified,
) {
  companion object {
    @Composable
    fun defaultBackground(darkTheme: Boolean): ArticlesBackground {
      return if (darkTheme) {
        ArticlesBackground(
          color = colorResource(id = R.color.background_dark),
          tonalElevation = 0.dp,
        )
      } else {
        ArticlesBackground(
          color = colorResource(id = R.color.background),
          tonalElevation = 0.dp,
        )
      }
    }
  }
}

val LocalBackgroundTheme: ProvidableCompositionLocal<ArticlesBackground> =
  staticCompositionLocalOf { ArticlesBackground() }
