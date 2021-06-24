package com.github.jamesdeperio.moviedb.android.ui.feature.home

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.github.jamesdeperio.moviedb.*
import com.github.jamesdeperio.moviedb.android.databinding.FragmentHomeBinding
import com.github.jamesdeperio.moviedb.android.ui.util.SvgSoftwareLayerSetter
import com.github.jamesdeperio.moviedb.model.domain.trending.Trending
import com.github.jamesdeperio.moviedb.model.ui.HomeItem
import com.github.jamesdeperio.moviedb.viewmodel.ViewActionObserver
import com.github.jamesdeperio.moviedb.viewmodel.ViewStateObserver
import com.github.jamesdeperio.moviedb.viewmodel.home.HomeAction
import com.github.jamesdeperio.moviedb.viewmodel.home.HomeState
import com.github.jamesdeperio.moviedb.viewmodel.home.HomeViewModel
import dagger.android.support.DaggerFragment
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import javax.inject.Inject
import javax.inject.Named


class HomeFragment : DaggerFragment(),ViewStateObserver<HomeState>,ViewActionObserver<HomeAction> {
    //region DEPENDENCY INJECTION AND VARIABLES
    @Inject lateinit var viewModel: HomeViewModel
    @Inject @Named(NAME_POPULAR_ADAPTER) lateinit var popularAdapter: HomeAdapter<HomeItem>
    @Inject @Named(NAME_TOP_RATED_ADAPTER) lateinit var topRatedAdapter: HomeAdapter<HomeItem>
    @Inject @Named(NAME_MORE_ADAPTER) lateinit var moreAdapter: HomeAdapter<HomeItem>
    @Inject @Named(NAME_TRENDING_ADAPTER) lateinit var trendingAdapter: HomeAdapter<HomeItem>
    private lateinit var binding: FragmentHomeBinding
    //endregion

    //region STATIC METHODS AND VARIABLES
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment().apply {
            arguments = Bundle()
        }
    }
    //endregion

    //region LIFECYCLE
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        setupBanner()
        setupPopularSection()
        setupTopRatedSection()
        setupMoreSection()
        setupTrendingSection()
        setupFooterSection()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPopularTV()
        viewModel.loadTopRatedTV()
        viewModel.loadNowPlayingMovie()
        viewModel.loadTrending(Trending.DAY)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeRunningThread()
    }
    //endregion

    //region VIEW CONFIGURATION
    private fun setupPopularSection() {
        binding.tnPopular.addTab("On TV") { onUserTrigger(HomeAction.OnPopularOnTVSelected) }
        binding.tnPopular.addTab("In Theaters") { onUserTrigger(HomeAction.OnPopularForInTheatersSelected) }

        binding.rvPopularItem.adapter = AlphaInAnimationAdapter(popularAdapter).apply {
            setDuration(1000)
            setInterpolator(OvershootInterpolator())
            setFirstOnly(false)
        }
        binding.rvPopularItem.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

    }

    private fun setupTopRatedSection() {
        binding.tnTopRated.addTab("On TV") { onUserTrigger(HomeAction.OnTopRatedOnTVSelected) }
        binding.tnTopRated.addTab("In Theaters") { onUserTrigger(HomeAction.OnTopRatedForInTheatersSelected) }

        binding.rvTopRatedItem.adapter = AlphaInAnimationAdapter(topRatedAdapter).apply {
            setDuration(1000)
            setInterpolator(OvershootInterpolator())
            setFirstOnly(false)
        }
        binding.rvTopRatedItem.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

    }

    private fun setupMoreSection() {
        binding.tnMore.addTab("Now Playing") { onUserTrigger(HomeAction.OnNowPlayingSelected) }
        binding.tnMore.addTab("Upcoming") { onUserTrigger(HomeAction.OnUpcomingSelected) }
        binding.tnMore.addTab("Airing Today") { onUserTrigger(HomeAction.OnAiringTodaySelected) }
        binding.tnMore.addTab("On the Air") { onUserTrigger(HomeAction.OnTheAirSelected) }


        binding.rvMoreItem.adapter = AlphaInAnimationAdapter(moreAdapter).apply {
            setDuration(1000)
            setInterpolator(OvershootInterpolator())
            setFirstOnly(false)
        }
        binding.rvMoreItem.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)

    }

    private fun setupTrendingSection() {
        Glide.with(this)
            .`as`(PictureDrawable::class.java)
            .listener(SvgSoftwareLayerSetter())
            .centerCrop()
            .load(Uri.parse(TREND_BANNER))
            .into(binding.ivTrending)

        binding.tnTrending.addTab("Today") { onUserTrigger(HomeAction.OnTrendingByDaySelected) }
        binding.tnTrending.addTab("This Week") { onUserTrigger(HomeAction.OnTrendingByWeekSelected) }


        binding.rvTrendingItem.adapter = AlphaInAnimationAdapter(trendingAdapter).apply {
            setDuration(1000)
            setInterpolator(OvershootInterpolator())
            setFirstOnly(false)
        }
        binding.rvTrendingItem.layoutManager = GridLayoutManager(context,2,
            LinearLayoutManager.HORIZONTAL,false)

    }

    private fun setupFooterSection() {
        Glide.with(this)
            .`as`(PictureDrawable::class.java)
            .listener(SvgSoftwareLayerSetter())
            .centerCrop()
            .load(Uri.parse(LOGO_FOOTER))
            .into(binding.ivFooterLogo)
    }

    private fun setupBanner() {
        Glide.with(requireContext())
            .asDrawable()
            .centerCrop()
            .load(IMAGE_BANNER)
            .into(binding.ivBanner)
    }

    //endregion

    //region USER ACTION
    override fun onUserTrigger(i: HomeAction) {
        Log.d(HomeAction::class.simpleName,i.toString())
        when(i) {
            is HomeAction.OnPopularOnTVSelected -> {
                viewModel.loadPopularTV()
            }
            is HomeAction.OnPopularForInTheatersSelected -> {
                viewModel.loadPopularTheater()
            }
            is HomeAction.OnTopRatedOnTVSelected -> {
                viewModel.loadTopRatedTV()
            }
            is HomeAction.OnTopRatedForInTheatersSelected -> {
                viewModel.loadTopRatedMovie()
            }
            is HomeAction.OnNowPlayingSelected -> {
                viewModel.loadNowPlayingMovie()
            }
            is HomeAction.OnUpcomingSelected -> {
                viewModel.loadUpcomingMovie()
            }
            is HomeAction.OnTheAirSelected -> {
                viewModel.loadOnTheAirTV()
            }
            is HomeAction.OnAiringTodaySelected -> {
                viewModel.loadAiringTodayTV()
            }
            is HomeAction.OnTrendingByDaySelected -> {
                viewModel.loadTrending(Trending.DAY)
            }
            is HomeAction.OnTrendingByWeekSelected -> {
                viewModel.loadTrending(Trending.WEEK)
            }
            else -> {
                throw RuntimeException("Invalid home action!")
            }
        }
    }
    //endregion

    //region VIEWMODEL CALLBACK
    override fun onObserve(i: HomeState) {
        Log.d(HomeState::class.simpleName,i.toString())
        when(i) {
            is HomeState.LoadPopularItem -> {
                popularAdapter.data.clear()
                popularAdapter.data = i.data.toMutableList()
                popularAdapter.notifyDataSetChanged()
                binding.rvPopularItem.scrollToPosition(0)
            }
            is HomeState.FailedLoadPopularItem -> {
                //todo
            }
            is HomeState.LoadTopRatedItem -> {
                topRatedAdapter.data.clear()
                topRatedAdapter.data = i.data.toMutableList()
                topRatedAdapter.notifyDataSetChanged()
                binding.rvTopRatedItem.scrollToPosition(0)
            }
            is HomeState.FailedLoadTopRatedItem -> {
                //todo
            }
            is HomeState.LoadMoreMovieTVShowtem -> {
                moreAdapter.data.clear()
                moreAdapter.data = i.data.toMutableList()
                moreAdapter.notifyDataSetChanged()
                binding.rvMoreItem.scrollToPosition(0)
            }
            is HomeState.FailedLoadoreMovieTVShowItem -> {
                //todo
            }
            is HomeState.LoadTrendingtem -> {
                trendingAdapter.data.clear()
                trendingAdapter.data = i.data.toMutableList()
                trendingAdapter.notifyDataSetChanged()
                binding.rvTrendingItem.scrollToPosition(0)
            }
            is HomeState.FailedLoadTrendingItem -> {
                //todo
            }

            else -> {
                throw RuntimeException("Invalid home state!")
            }
        }
    }
    //endregion
}