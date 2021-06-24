package com.github.jamesdeperio.moviedb.model.domain.topratedmovie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedMovieData (
    @SerialName("adult") var adult : Boolean,
    @SerialName("backdrop_path") var backdropPath : String?=null,
    @SerialName("genre_ids") var genreIds : List<Int>?=null,
    @SerialName("id") var id : Int,
    @SerialName("original_language") var originalLanguage : String?=null,
    @SerialName("original_title") var originalTitle : String?=null,
    @SerialName("overview") var overview : String?=null,
    @SerialName("popularity") var popularity : Double?=null,
    @SerialName("poster_path") var posterPath : String?=null,
    @SerialName("release_date") var releaseDate : String?=null,
    @SerialName("title") var title : String?=null,
    @SerialName("video") var video : Boolean,
    @SerialName("vote_average") var voteAverage : Double?=null,
    @SerialName("vote_count") var voteCount : Int
)
