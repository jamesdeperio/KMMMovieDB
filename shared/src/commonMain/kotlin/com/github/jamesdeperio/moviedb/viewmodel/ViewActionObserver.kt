package com.github.jamesdeperio.moviedb.viewmodel

interface ViewActionObserver<T> {
    fun onUserTrigger(i:T)
}