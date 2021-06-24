package com.github.jamesdeperio.moviedb.android.app.module

import android.app.Application
import android.content.Context
import com.github.jamesdeperio.moviedb.android.app.scope.ApplicationScope
import com.github.jamesdeperio.moviedb.network.NetworkManager
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class ApplicationBindingModule {
    @Binds
    internal abstract fun bindContext(application: Application): Context

    companion object Provider {
        @ApplicationScope
        @Provides
        fun provideNetworkManager(): NetworkManager = NetworkManager()
    }

}