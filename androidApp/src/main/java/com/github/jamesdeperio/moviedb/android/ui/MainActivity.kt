 package com.github.jamesdeperio.moviedb.android.ui

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.github.jamesdeperio.moviedb.LOGO_HEADER
import com.github.jamesdeperio.moviedb.android.R
import com.github.jamesdeperio.moviedb.android.databinding.ActivityMainBinding
import com.github.jamesdeperio.moviedb.android.ui.feature.home.HomeFragment
import com.github.jamesdeperio.moviedb.android.ui.util.SvgSoftwareLayerSetter
import dagger.android.support.DaggerAppCompatActivity

 class MainActivity : DaggerAppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .`as`(PictureDrawable::class.java)
            .listener(SvgSoftwareLayerSetter())
            .centerCrop()
            .load( Uri.parse(LOGO_HEADER))
            .into(binding.ivHeaderLogo)

        supportFragmentManager.commit {
            addToBackStack(HomeFragment::class.simpleName)
            replace(R.id.fragmentContainer,HomeFragment.newInstance(), HomeFragment::class.simpleName)
        }

    }

}
