package com.github.jamesdeperio.moviedb.viewmodel.home

sealed class HomeAction {
    object OnNowPlayingSelected:HomeAction()
    object OnUpcomingSelected:HomeAction()
    object OnAiringTodaySelected:HomeAction()
    object OnTheAirSelected:HomeAction()


    object OnTopRatedForInTheatersSelected:HomeAction()
    object OnTopRatedOnTVSelected:HomeAction()

    object OnPopularOnTVSelected:HomeAction()
    object OnPopularForInTheatersSelected:HomeAction()

    object OnTrendingByDaySelected:HomeAction()
    object OnTrendingByWeekSelected:HomeAction()

}
