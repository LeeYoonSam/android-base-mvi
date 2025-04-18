package com.ys.core.model

object MockUtils {
    val mockArticle: Article
        get() = Article(
            title = "Titles",
            content = "Content",
            description = "description",
            author = "Author",
            date = "Date",
            cover = "Cover",
        )
}