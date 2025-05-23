package com.ys.core.navigation

import android.net.Uri
import android.os.Bundle
import androidx.core.os.BundleCompat
import androidx.navigation.NavType
import com.ys.core.model.Article
import kotlinx.serialization.json.Json

object ArticlesType : NavType<Article>(isNullableAllowed = false) {
    override fun put(bundle: Bundle, key: String, value: Article) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): Article? =
        BundleCompat.getParcelable(bundle, key, Article::class.java)

    override fun parseValue(value: String): Article {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: Article): String = Uri.encode(Json.encodeToString(value))
}