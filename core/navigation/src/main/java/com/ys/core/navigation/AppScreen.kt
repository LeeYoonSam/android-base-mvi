package com.ys.core.navigation

import com.ys.core.model.Article
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface AppScreen {
    @Serializable
    data object HomeScreen : AppScreen

    @Serializable
    data class ArticleScreen(val article: Article) : AppScreen {
        companion object {
            val typeMap = mapOf(typeOf<Article>() to ArticlesType)
        }
    }
}