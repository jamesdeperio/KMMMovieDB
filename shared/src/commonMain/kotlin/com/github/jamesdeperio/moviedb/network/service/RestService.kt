package com.github.jamesdeperio.moviedb.network.service

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
import com.github.jamesdeperio.moviedb.network.repository.RestRepository

//access db/pref if needed
//map data if needed

class RestService(private val restRepository: RestRepository) {

    //region POPULAR ITEMS
    suspend fun getPopularTheater(): List<PopularMovieData>
            = restRepository.getPopularTheater().datas?:ArrayList()

    suspend fun getPopularTV(): List<PopularTVData>
            = restRepository.getPopularTV().datas?:ArrayList()

    //endregion

    //region TOP RATED ITEMS
    suspend fun getTopRatedTV(): List<TopRatedTVData>
            = restRepository.getTopRatedTV().datas?:ArrayList()

    suspend fun getTopRatedMovie(): List<TopRatedMovieData>
            = restRepository.getTopRatedMovie().datas?:ArrayList()

    //engregion

    //region MORE MOVIES AND TV SHOW
    suspend fun getNowPlayingMovie(): List<NowPlayingMovieData>
            = restRepository.getNowPlayingMovie().datas?:ArrayList()

    suspend fun getUpComingMovie(): List<UpcomingMovieData>
            = restRepository.getUpcomingMovie().datas?:ArrayList()

    suspend fun getAiringTodayTV(): List<AiringTodayData>
            = restRepository.getAiringTodayTV().datas?:ArrayList()

    suspend fun getOntheAirTV(): List<OnTheAirData>
            = restRepository.getOntheAirTV().datas?:ArrayList()

    //endregion


    //region TRENDING
    suspend fun getTrending(trending: Trending): List<TrendingData>
            = restRepository.getTrending(trending).datas?:ArrayList()

    //endregion


}