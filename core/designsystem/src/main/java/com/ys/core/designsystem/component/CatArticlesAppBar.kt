package com.ys.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ys.core.designsystem.R
import com.ys.core.designsystem.theme.ArticlesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesAppBar(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.app_name),
    navigationIcon: @Composable () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = ArticlesTheme.colors.textPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = navigationIcon,
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent,
        ),
    )
}

@Preview
@Composable
private fun ArticlesAppBarPreview() {
    ArticlesTheme {
        ArticlesAppBar(modifier = Modifier.background(ArticlesTheme.colors.primary))
    }
}
