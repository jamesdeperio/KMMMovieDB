package com.github.jamesdeperio.moviedb.model.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class APIResponse<T> (
   @SerialName("results") var datas : List<T>?
)