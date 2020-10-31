package com.tuppersoft.marvel.core.extension

import android.view.View

/**
 * return if a view is visible or not
 */
fun View.isVisible() = this.visibility == View.VISIBLE

/**
 * Show view
 */
fun View.visible() {
    this.post { this.visibility = View.VISIBLE }
}

/**
 * Hide view
 */
fun View.gone() {
    this.post { this.visibility = View.GONE }
}

/**
 * Hide view
 */
fun View.invisible() {
    this.post { this.visibility = View.INVISIBLE }
}
