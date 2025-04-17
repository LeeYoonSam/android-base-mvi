package com.ys.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Rect

val boundsTransForm = { _: Rect, _: Rect -> tween<Rect>(550) }