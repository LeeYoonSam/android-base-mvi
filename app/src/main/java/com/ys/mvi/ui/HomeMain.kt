package com.ys.mvi.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.ys.core.designsystem.theme.ArticlesTheme
import com.ys.core.navigation.AppComposeNavigator
import com.ys.core.navigation.AppScreen
import com.ys.mvi.navigation.AppNavHost

@Composable
fun HomeMain(composeNavigator: AppComposeNavigator<AppScreen>) {
    ArticlesTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        AppNavHost(navHostController)
    }
}