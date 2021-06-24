package com.github.jamesdeperio.moviedb.android.app
import android.app.Application
import com.github.jamesdeperio.moviedb.android.app.module.ActivityBindingModule
import com.github.jamesdeperio.moviedb.android.app.module.ApplicationBindingModule
import com.github.jamesdeperio.moviedb.android.app.module.FragmentBindingModule
import com.github.jamesdeperio.moviedb.android.app.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationBindingModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class
   // ServiceBindingModule::class,
  //  BroadcastReceiverBindingModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {
    override fun inject(instance: DaggerApplication?)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}