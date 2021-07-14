package com.github.jamesdeperio.moviedb

import com.github.jamesdeperio.moviedb.network.NetworkManager
import kotlin.test.Test

class IosGreetingTest {

    @Test
    fun testExample() {
        val networkManager = NetworkManager()
        networkManager.restService
        assert(true)
    }
}