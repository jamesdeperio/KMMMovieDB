package com.github.jamesdeperio.moviedb.model.domain.trending

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingData (
   @SerialName("genre_ids") var genreIds : List<Int>?=null,
   @SerialName("original_language") var originalLanguage : String?=null,
   @SerialName("original_title") var originalTitle : String?=null,
   @SerialName("poster_path") var posterPath : String?=null,
   @SerialName("video") var video : Boolean?=null,
   @SerialName("vote_average") var voteAverage : Double?=null,
   @SerialName("overview") var overview : String?=null,
   @SerialName("release_date") var releaseDate : String?=null,
   @SerialName("vote_count") var voteCount : Int,
   @SerialName("title") var title : String?=null,
   @SerialName("adult") var adult : Boolean?=null,
   @SerialName("backdrop_path") var backdropPath : String?=null,
   @SerialName("id") var id : Int,
   @SerialName("popularity") var popularity : Double?=null,
   @SerialName("media_type") var mediaType : String?=null

)