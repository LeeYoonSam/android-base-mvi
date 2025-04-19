package com.ys.feature.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ys.core.data.repository.ArticlesRepository
import com.ys.core.model.Article
import com.ys.core.navigation.AppComposeNavigator
import com.ys.core.navigation.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: ArticlesRepository,
    private val navigator: AppComposeNavigator<AppScreen>
) : ViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<HomeUiState> = repository.fetchArticles()
        .mapLatest { response ->
            HomeUiState.Success(response)

//            if (response.isSuccessful) {
//                HomeUiState.Success(response.body() ?: emptyList())
//            } else {
//                HomeUiState.Error(response.message())
//            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading,
        )

    fun navigateToDetails(article: Article) {
    }
}

@Stable
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val articles: List<Article>) : HomeUiState
    data class Error(val message: String?) : HomeUiState
}