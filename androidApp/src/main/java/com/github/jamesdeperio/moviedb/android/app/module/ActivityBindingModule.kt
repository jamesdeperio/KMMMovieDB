package com.github.jamesdeperio.moviedb.android.app.module

import com.github.jamesdeperio.moviedb.android.ui.MainActivity
import com.github.jamesdeperio.moviedb.android.ui.MainModule
import com.github.jamesdeperio.moviedb.android.app.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainInjector(): MainActivity

}
