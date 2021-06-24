package com.github.jamesdeperio.moviedb.android.ui.base

import android.os.SystemClock
import android.view.View
import kotlin.math.abs

abstract class OnThrottleClickListener(private val intervalInMillis: Long) : View.OnClickListener {
    private var prevTimestamp: Long = 0
    abstract fun onThrottleClick(v: View)

    override fun onClick(clickedView: View) {
        val currentTimestamp = SystemClock.uptimeMillis()
        if (prevTimestamp == 0L || abs(currentTimestamp - prevTimestamp) > intervalInMillis) {
            prevTimestamp = currentTimestamp
            onThrottleClick(clickedView)
        }
    }
}

fun View.setOnThrottleClickListener(delay:Long=750,callback:(view:View)->Unit){
    this.setOnClickListener(object :OnThrottleClickListener(delay){
        override fun onThrottleClick(v: View) {
            callback(v)
        }
    })
}