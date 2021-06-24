package com.github.jamesdeperio.moviedb.android.ui.feature.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.github.jamesdeperio.moviedb.IMAGE_BASE_URL
import com.github.jamesdeperio.moviedb.android.databinding.ListHomeItemBinding
import com.github.jamesdeperio.moviedb.android.ui.base.ViewHolder
import com.github.jamesdeperio.moviedb.android.ui.base.setOnThrottleClickListener
import com.github.jamesdeperio.moviedb.model.ui.HomeItem


class HomeItemViewHolder(
    private val adapter: HomeAdapter<HomeItem>,
    private val context: Context?
): ViewHolder() {

    private lateinit var binding: ListHomeItemBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(itemView: View, position: Int) {
        binding = ListHomeItemBinding.bind(itemView)
        val data = adapter.data[position]

        if (context!=null) {
            binding.loaderPoster.visibility= View.VISIBLE
            binding.loaderPoster.startShimmer()
            Glide.with(context)
                .asDrawable()
                .centerCrop()
                .load("$IMAGE_BASE_URL${data.posterPath}")
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        binding.loaderPoster.hideShimmer()
                        binding.loaderPoster.visibility= View.GONE
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        binding.loaderPoster.hideShimmer()
                        binding.loaderPoster.visibility= View.GONE
                        return false
                    }

                })
                .into(binding.ivPoster)
        }


        binding.ivPoster.setOnThrottleClickListener {

        }

        binding.ivPoster.setOnClickListener {
            YoYo.with(Techniques.Tada)
                .duration(400)
                .playOn(itemView)
        }

        if(data.voteAverage==null) {
            binding.tvVote.text =  "--"
            binding.pbVote.setProgress(0,true)
        } else {
            when {
                (data.voteAverage!! * 10).toInt() < 45 -> {
                    binding.pbVote.setIndicatorColor(Color.parseColor("#BA0000"))
                }
                (data.voteAverage!! * 10).toInt() < 70 -> {
                    binding.pbVote.setIndicatorColor(Color.parseColor("#FFEB3B"))
                }
                else -> {
                    binding.pbVote.setIndicatorColor(Color.parseColor("#4CAF50"))
                }
            }
            val value =(data.voteAverage!! * 10).toInt().toString()
            val finalValue = SpannableStringBuilder(value)
            finalValue.append("%")
            finalValue.setSpan(SuperscriptSpan(), value.length, finalValue.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            finalValue.setSpan(RelativeSizeSpan(0.75f), value.length, finalValue.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.tvVote.text =  finalValue
            binding.pbVote.progress=value.toInt()
        }

    }
}