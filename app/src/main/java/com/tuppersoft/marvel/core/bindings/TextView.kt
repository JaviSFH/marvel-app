package com.tuppersoft.marvel.core.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.tuppersoft.marvel.R.string

@BindingAdapter("description")
fun setImageDrawable(view: TextView, description: String?) {

    if (description.isNullOrEmpty()) {
        view.text = view.context.getString(string.no_description)
    } else {
        view.text = description
    }
}


