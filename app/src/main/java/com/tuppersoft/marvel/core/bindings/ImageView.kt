package com.tuppersoft.marvel.core.bindings

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.tuppersoft.marvel.core.extension.loadFromUrl
import com.tuppersoft.marvel.core.extension.logd

@BindingAdapter("android:src")
fun setImageDrawable(imageView: ImageView, url: String?) {

    val newUrl = if (url?.contains("image_not_available") == true) {
        ""
    } else {
        url
    }

    imageView.loadFromUrl(newUrl)
}

@BindingAdapter("android:src")
fun setImageDrawable(imageView: ImageView, drawable: Drawable) {
    imageView.setImageDrawable(drawable)
}

@BindingAdapter("android:src")
fun setImageResource(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}
