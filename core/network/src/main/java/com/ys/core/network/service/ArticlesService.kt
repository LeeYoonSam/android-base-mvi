package com.ys.core.network.service

import com.ys.core.model.Article
import retrofit2.Response
import retrofit2.http.GET

interface ArticlesService {
    @GET("articles.json")
    suspend fun fetchArticles(): List<Article>
}
