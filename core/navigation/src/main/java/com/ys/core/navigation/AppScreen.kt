package com.ys.core.navigation

import kotlinx.serialization.Serializable

sealed interface AppScreen {

    @Serializable
    data object HomeScreen : AppScreen
}