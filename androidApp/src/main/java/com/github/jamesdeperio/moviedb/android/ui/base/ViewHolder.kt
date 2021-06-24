package com.github.jamesdeperio.moviedb.android.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder  {
    lateinit var viewHolder: RecyclerView.ViewHolder
    var layout: Int = 0
    fun setView(view: View) {
        viewHolder =  Holder(view)
    }
    fun setContentView(layoutID: Int) {
        layout = layoutID
    }

    abstract fun onBindViewHolder(itemView: View, position: Int)

    inner class Holder(view: View):  RecyclerView.ViewHolder(view)
}