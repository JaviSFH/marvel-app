package com.tuppersoft.marvel.core.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

inline fun <reified T : ViewDataBinding> ViewGroup.inflateDataBinding(
    @LayoutRes id: Int,
    attachToRoot: Boolean = false
): T {
    return DataBindingUtil.inflate(
        LayoutInflater.from(this.context),
        id,
        this,
        false
    )
}
