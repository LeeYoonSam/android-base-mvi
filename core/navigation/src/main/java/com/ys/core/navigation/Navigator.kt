package com.ys.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onSubscription

abstract class Navigator {
    val navigationCommands = MutableSharedFlow<NavigationCommand>(extraBufferCapacity = Int.MAX_VALUE)

    // 뷰모델이 초기 컴포지션 전에 탐색 결과를 관찰하기 시작하고 나중에 탐색 결과를 가져올 수 있도록 하기 위해 여기서는 StateFlow를 사용합니다.
    val navControllerFlow = MutableStateFlow<NavController?>(null)

    fun navigateUp() {
        navigationCommands.tryEmit(NavigationCommand.NavigateUp)
    }
}

abstract class AppComposeNavigator<T : Any> : Navigator() {
    abstract fun navigate(route: T, optionsBuilder: (NavOptionsBuilder.() -> Unit)? = null)
    abstract fun <R> navigateBackWithResult(key: String, result: R, route: T?)

    abstract fun popUpTo(route: T, inclusive: Boolean)
    abstract fun navigateAndClearBackStack(route: T)

    suspend fun handleNavigationCommands(navController: NavController) {
        navigationCommands
            .onSubscription { this@AppComposeNavigator.navControllerFlow.value = navController }
            .onCompletion { this@AppComposeNavigator.navControllerFlow.value = null }
            .collect { navController.handleComposeNavigationCommand(it) }
    }

    private fun NavController.handleComposeNavigationCommand(navigationCommand: NavigationCommand) {
        when (navigationCommand) {
            is ComposeNavigationCommand.NavigateToRoute<*> -> {
                navigate(navigationCommand.route, navigationCommand.options)
            }

            NavigationCommand.NavigateUp -> navigateUp()

            is ComposeNavigationCommand.PopUpToRoute<*> -> popBackStack(
                navigationCommand.route,
                navigationCommand.inclusive,
            )

            is ComposeNavigationCommand.NavigateUpWithResult<*, *> -> {
                navUpWithResult(navigationCommand)
            }
        }
    }
    private fun NavController.navUpWithResult(
        navigationCommand: ComposeNavigationCommand.NavigateUpWithResult<*, *>,
    ) {
        val backStackEntry =
            navigationCommand.route?.let { getBackStackEntry(it) }
                ?: previousBackStackEntry
        backStackEntry?.savedStateHandle?.set(
            navigationCommand.key,
            navigationCommand.result,
        )

        navigationCommand.route?.let {
            popBackStack(it, false)
        } ?: navigateUp()
    }
}