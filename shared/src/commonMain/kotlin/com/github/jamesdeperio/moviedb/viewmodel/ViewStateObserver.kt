package com.github.jamesdeperio.moviedb.viewmodel

interface ViewStateObserver<T> {
    fun onObserve(i:T)
}