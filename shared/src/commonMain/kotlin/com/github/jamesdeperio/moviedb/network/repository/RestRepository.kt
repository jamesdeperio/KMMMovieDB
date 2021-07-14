package com.github.jamesdeperio.moviedb.network.repository

import com.github.jamesdeperio.moviedb.model.domain.APIResponse
import com.github.jamesdeperio.moviedb.model.domain.airingtodaytv.AiringTodayData
import com.github.jamesdeperio.moviedb.model.domain.nowplayingmovie.NowPlayingMovieData
import com.github.jamesdeperio.moviedb.model.domain.ontheairtv.OnTheAirData
import com.github.jamesdeperio.moviedb.model.domain.popularmovie.PopularMovieData
import com.github.jamesdeperio.moviedb.model.domain.populartv.PopularTVData
import com.github.jamesdeperio.moviedb.model.domain.topratedmovie.TopRatedMovieData
import com.github.jamesdeperio.moviedb.model.domain.topratedtv.TopRatedTVData
import com.github.jamesdeperio.moviedb.model.domain.trending.Trending
import com.github.jamesdeperio.moviedb.model.domain.trending.TrendingData
import com.github.jamesdeperio.moviedb.model.domain.upcomingmovie.UpcomingMovieData
import com.github.jamesdeperio.moviedb.network.NetworkManager
import io.ktor.client.request.*

class RestRepository(private val networkManager: NetworkManager) {

    //region POPULAR ITEM
    suspend fun getPopularTheater (): APIResponse<PopularMovieData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/movie/popular?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }

    suspend fun getPopularTV (): APIResponse<PopularTVData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/tv/popular?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }

    //endregion


    //region TOP RATED ITEM
    suspend fun getTopRatedTV(): APIResponse<TopRatedTVData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/tv/top_rated?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }

    suspend fun getTopRatedMovie(): APIResponse<TopRatedMovieData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/movie/top_rated?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }
    //endregion

    //region MORE MOVIES AND TV SHOW
    suspend fun getNowPlayingMovie(): APIResponse< NowPlayingMovieData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/movie/now_playing?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }

    suspend fun getUpcomingMovie(): APIResponse< UpcomingMovieData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/movie/upcoming?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }

    suspend fun getAiringTodayTV(): APIResponse< AiringTodayData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/tv/airing_today?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }

    suspend fun getOntheAirTV(): APIResponse<OnTheAirData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/tv/on_the_air?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }
    //endregion

    //region Trending
    suspend fun getTrending(trending: Trending): APIResponse<TrendingData> =
         NetworkManager.httpClient.get("${NetworkManager.BASE_URL}/3/trending/all/${trending.name}?api_key=${NetworkManager.API_KEY}&language=en-US") {
            headers {
                networkManager.setupSecurityHeader(this)
            }
        }
    //endregion
}