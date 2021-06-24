package com.github.jamesdeperio.moviedb.android.ui.feature.home

import com.github.jamesdeperio.moviedb.android.ui.base.Adapter

class HomeAdapter<T>: Adapter() {
    var data: MutableList<T> = ArrayList()

    override fun getItemCount(): Int = data.size
}