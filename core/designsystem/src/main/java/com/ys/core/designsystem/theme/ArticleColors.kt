package com.ys.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.ys.core.designsystem.R

data class ArticleColors(
    val primary: Color,
    val colorAccent: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val divider: Color,
    val iconTint: Color,
    val textOnPrimary: Color,
) {
    companion object {
        @Composable
        fun defaultLightColors(): ArticleColors = ArticleColors(
            primary = colorResource(id = R.color.colorPrimary),
            colorAccent = colorResource(id = R.color.colorAccent),
            background = colorResource(id = R.color.background),
            surface = colorResource(id = R.color.surface),
            textPrimary = colorResource(id = R.color.text_primary),
            divider = colorResource(id = R.color.divider),
            iconTint = colorResource(id = R.color.icon_tint),
            textOnPrimary = colorResource(id = R.color.text_on_primary),
        )

        @Composable
        fun defaultDarkColors(): ArticleColors = ArticleColors(
            primary = colorResource(id = R.color.colorPrimaryDark),
            colorAccent = colorResource(id = R.color.colorAccent),
            background = colorResource(id = R.color.background_dark),
            surface = colorResource(id = R.color.surface_dark),
            textPrimary = colorResource(id = R.color.text_primary_dark),
            divider = colorResource(id = R.color.divider_dark),
            iconTint = colorResource(id = R.color.icon_tint_dark),
            textOnPrimary = colorResource(id = R.color.text_on_primary_dark),
        )
    }
}