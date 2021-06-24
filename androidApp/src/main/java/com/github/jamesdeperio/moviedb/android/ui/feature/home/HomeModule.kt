package com.github.jamesdeperio.moviedb.android.ui.feature.home

import com.github.jamesdeperio.moviedb.NAME_MORE_ADAPTER
import com.github.jamesdeperio.moviedb.NAME_POPULAR_ADAPTER
import com.github.jamesdeperio.moviedb.NAME_TOP_RATED_ADAPTER
import com.github.jamesdeperio.moviedb.NAME_TRENDING_ADAPTER
import com.github.jamesdeperio.moviedb.android.R
import com.github.jamesdeperio.moviedb.android.app.scope.FragmentScope
import com.github.jamesdeperio.moviedb.model.ui.HomeItem
import com.github.jamesdeperio.moviedb.network.NetworkManager
import com.github.jamesdeperio.moviedb.viewmodel.ViewStateObserver
import com.github.jamesdeperio.moviedb.viewmodel.home.HomeState
import com.github.jamesdeperio.moviedb.viewmodel.home.HomeViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
object HomeModule {

    @FragmentScope
    @Named(NAME_POPULAR_ADAPTER)
    @Provides
    fun providePopularAdapter(fragment: HomeFragment): HomeAdapter<HomeItem> {
        val adapter = HomeAdapter<HomeItem>()
        val viewHolder = HomeItemViewHolder(adapter,fragment.context)
        viewHolder.setContentView(R.layout.list_home_item)
        adapter.addViewHolder(viewHolder)

        return adapter
    }

    @FragmentScope
    @Named(NAME_TOP_RATED_ADAPTER)
    @Provides
    fun provideTopRatedAdapter(fragment: HomeFragment): HomeAdapter<HomeItem> {
        val adapter = HomeAdapter<HomeItem>()
        val viewHolder = HomeItemViewHolder(adapter,fragment.context)
        viewHolder.setContentView(R.layout.list_home_item)
        adapter.addViewHolder(viewHolder)
        return adapter
    }

    @FragmentScope
    @Named(NAME_MORE_ADAPTER)
    @Provides
    fun provideMoreAdapter(fragment: HomeFragment): HomeAdapter<HomeItem> {
        val adapter = HomeAdapter<HomeItem>()
        val viewHolder = HomeItemWideViewHolder(adapter,fragment.context)
        viewHolder.setContentView(R.layout.list_home_wide_item)
        adapter.addViewHolder(viewHolder)
        return adapter
    }

    @FragmentScope
    @Named(NAME_TRENDING_ADAPTER)
    @Provides
    fun provideTrendingAdapter(fragment: HomeFragment): HomeAdapter<HomeItem> {
        val adapter = HomeAdapter<HomeItem>()
        val viewHolder = HomeItemViewHolder(adapter,fragment.context)
        viewHolder.setContentView(R.layout.list_home_item)
        adapter.addViewHolder(viewHolder)
        return adapter
    }

    @FragmentScope
    @Provides
    fun provideViewModel(networkManager: NetworkManager,fragment: HomeFragment): HomeViewModel =
        HomeViewModel(networkManager.restService,fragment as ViewStateObserver<HomeState>)
}