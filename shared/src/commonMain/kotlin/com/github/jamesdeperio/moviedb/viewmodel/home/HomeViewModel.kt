package com.github.jamesdeperio.moviedb.viewmodel.home

import com.github.jamesdeperio.moviedb.ApplicationDispatcher
import com.github.jamesdeperio.moviedb.model.domain.trending.Trending
import com.github.jamesdeperio.moviedb.model.ui.HomeItem
import com.github.jamesdeperio.moviedb.network.service.RestService
import com.github.jamesdeperio.moviedb.viewmodel.ViewStateObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel(
    private val restService: RestService,
    private val observer: ViewStateObserver<HomeState>
) {

    //region VARIABLES
    private val job = Job()
    private val uiScope = CoroutineScope(ApplicationDispatcher + job)

    //endregion

    fun disposeRunningThread() {
        job.cancel()
    }

    //region POPULAR ITEMS
    fun loadPopularTheater() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getPopularTheater().forEach {
                        items.add(HomeItem(posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            //httpClient.close()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadPopularItem(throwable!!)
                else HomeState.LoadPopularItem(items)
            )
        }
    }

    fun loadPopularTV() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getPopularTV().forEach {
                        items.add(HomeItem(posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadPopularItem(throwable!!)
                else HomeState.LoadPopularItem(items)
            )
        }
    }
    //endregion

    //region TOP RATED ITEMS
    fun loadTopRatedTV() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getTopRatedTV().forEach {
                        items.add(HomeItem(posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadTopRatedItem(throwable!!)
                else HomeState.LoadTopRatedItem(items)
            )
        }
    }

    fun loadTopRatedMovie() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getTopRatedMovie().forEach {
                        items.add(HomeItem(posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadTopRatedItem(throwable!!)
                else HomeState.LoadTopRatedItem(items)
            )
        }
    }
    //endregion

    //region MORE MOVIES AND TV SHOW
    fun loadNowPlayingMovie() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getNowPlayingMovie().forEach {
                        items.add(HomeItem(title = it.title,posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadoreMovieTVShowItem(throwable!!)
                else HomeState.LoadMoreMovieTVShowtem(items)
            )
        }
    }

    fun loadUpcomingMovie() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getUpComingMovie().forEach {
                        items.add(HomeItem(title = it.title,posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadoreMovieTVShowItem(throwable!!)
                else HomeState.LoadMoreMovieTVShowtem(items)
            )
        }
    }

    fun loadOnTheAirTV() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getOntheAirTV().forEach {
                        items.add(HomeItem(title = it.name,posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadoreMovieTVShowItem(throwable!!)
                else HomeState.LoadMoreMovieTVShowtem(items)
            )
        }
    }

    fun loadAiringTodayTV() {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getAiringTodayTV().forEach {
                        items.add(HomeItem(title = it.name,posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadoreMovieTVShowItem(throwable!!)
                else HomeState.LoadMoreMovieTVShowtem(items)
            )
        }
    }
    //endregion


    //region TRENDING

    fun loadTrending(trending:Trending) {
        uiScope.launch {
            val items: MutableList<HomeItem> = ArrayList()
            var throwable : Throwable? = null
            val task = async {
                try {
                    restService.getTrending(trending).forEach {
                        items.add(HomeItem(posterPath = it.posterPath, voteAverage = it.voteAverage, data = it))
                    }
                    //handle custom error
                } catch (e:Exception){
                    throwable = e
                    //handle network/request error
                }
            }
            task.await()
            observer.onObserve(
                if (throwable!=null) HomeState.FailedLoadTrendingItem(throwable!!)
                else HomeState.LoadTrendingtem(items)
            )
        }
    }
    //endregion


}