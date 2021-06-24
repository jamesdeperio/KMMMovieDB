package com.github.jamesdeperio.moviedb.android.app

import com.github.jamesdeperio.moviedb.network.NetworkManager
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class MainApplication: DaggerApplication() {
    @Inject lateinit var networkManager: NetworkManager
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onTerminate() {
        super.onTerminate()
        networkManager.closeHttpClient()

    }
}