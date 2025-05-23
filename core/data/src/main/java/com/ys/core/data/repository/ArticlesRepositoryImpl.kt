package com.ys.core.data.repository

import com.ys.core.model.Article
import com.ys.core.network.AppDispatcher
import com.ys.core.network.AppDispatchers
import com.ys.core.network.service.ArticlesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class ArticlesRepositoryImpl @Inject constructor(
    private val articlesService: ArticlesService,
    @AppDispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ArticlesRepository {

    override fun fetchArticles(): Flow<List<Article>> = flow {
        val response = articlesService.fetchArticles()
        emit(response)
    }
        .catch { emit(emptyList()) }
        .flowOn(ioDispatcher)
}

