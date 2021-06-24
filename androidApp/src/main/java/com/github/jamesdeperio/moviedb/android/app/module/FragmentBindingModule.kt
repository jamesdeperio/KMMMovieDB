package com.github.jamesdeperio.moviedb.android.app.module

import com.github.jamesdeperio.moviedb.android.app.scope.FragmentScope
import com.github.jamesdeperio.moviedb.android.ui.feature.home.HomeFragment
import com.github.jamesdeperio.moviedb.android.ui.feature.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun homeInjector(): HomeFragment

}