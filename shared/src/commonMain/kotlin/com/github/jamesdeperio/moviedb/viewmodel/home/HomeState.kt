package com.github.jamesdeperio.moviedb.viewmodel.home

import com.github.jamesdeperio.moviedb.model.ui.HomeItem

sealed class HomeState {
    data class LoadPopularItem(val data: List<HomeItem>): HomeState()
    data class FailedLoadPopularItem(val throwable: Throwable): HomeState()

    data class LoadTopRatedItem(val data: List<HomeItem>): HomeState()
    data class FailedLoadTopRatedItem(val throwable: Throwable): HomeState()

    data class LoadMoreMovieTVShowtem(val data: List<HomeItem>): HomeState()
    data class FailedLoadoreMovieTVShowItem(val throwable: Throwable): HomeState()

    data class LoadTrendingtem(val data: List<HomeItem>): HomeState()
    data class FailedLoadTrendingItem(val throwable: Throwable): HomeState()

}
