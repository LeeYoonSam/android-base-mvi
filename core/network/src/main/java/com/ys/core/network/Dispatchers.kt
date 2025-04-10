package com.ys.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class AppDispatcher(val dispatchers: AppDispatchers)

enum class AppDispatchers {
    IO,
}
