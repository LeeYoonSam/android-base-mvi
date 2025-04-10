package com.ys.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Article(
    val title: String,
    val content: String,
    val description: String,
    val author: String,
    val date: String,
    val cover: String,
) : Parcelable