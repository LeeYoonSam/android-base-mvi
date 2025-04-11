package com.ys.core.data.repository

import com.ys.core.model.Article
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ArticlesRepository {
    fun fetchArticles(): Flow<Response<List<Article>>>
}