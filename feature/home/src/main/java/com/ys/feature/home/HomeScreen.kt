package com.ys.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ys.core.designsystem.component.ArticlesAppBar
import com.ys.core.designsystem.component.ArticlesCircularProgress
import com.ys.core.designsystem.theme.ArticlesTheme
import com.ys.core.model.Article
import com.ys.core.model.MockUtils

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        ArticlesAppBar(modifier = Modifier.background(ArticlesTheme.colors.primary))

        HomeContent(
            uiState = uiState,
            onNavigateToDetails = { homeViewModel.navigateToDetails(it) }
        )
    }
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    onNavigateToDetails: (Article) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState == HomeUiState.Loading) {
            ArticlesCircularProgress()
        } else if (uiState is HomeUiState.Success) {
            LazyVerticalGrid(
                modifier = Modifier.testTag("HomeList"),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(6.dp)
            ) {
                items(items = uiState.articles) { article ->
                    HomeCard(
                        article = article,
                        onNavigateToDetails = onNavigateToDetails
                    )
                }
            }
        }
    }
}

@Composable
fun HomeCard(
    article: Article,
    onNavigateToDetails: (Article) -> Unit
) {
    Box(
        modifier = Modifier
            .testTag("Home")
            .padding(8.dp)
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable { onNavigateToDetails(article) }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.Black.copy(alpha = 0.65f))
                .padding(12.dp),
            text = article.title,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    ArticlesTheme {
        AnimatedVisibility(visible = true, label = "") {
            HomeContent(
                uiState = HomeUiState.Success(List(10) { MockUtils.mockArticle }),
                onNavigateToDetails = {},
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
    ArticlesTheme {
        AnimatedVisibility(visible = true, label = "") {
            HomeCard(
                article = MockUtils.mockArticle,
                onNavigateToDetails = {},
            )
        }
    }
}
