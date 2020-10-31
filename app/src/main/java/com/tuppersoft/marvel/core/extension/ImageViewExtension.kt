package com.tuppersoft.marvel.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tuppersoft.marvel.R
import com.tuppersoft.marvel.core.platform.GlideApp

fun ImageView.loadFromUrl(url: String?, width: Int? = null, height: Int? = null) {

    if (width != null && height != null) {
        GlideApp.with(context).load(url).error(R.drawable.rsz_thum).placeholder(R.drawable.rsz_thum).diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(width, height).into(this)
    } else {
        GlideApp.with(context).load(url).error(R.drawable.rsz_thum).placeholder(R.drawable.rsz_thum).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
    }
}
