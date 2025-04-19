package com.ys.mvi.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ys.core.navigation.AppScreen
import com.ys.feature.home.HomeScreen

fun NavGraphBuilder.appNavigation() {
    composable<AppScreen.HomeScreen> {
        HomeScreen()
    }
}