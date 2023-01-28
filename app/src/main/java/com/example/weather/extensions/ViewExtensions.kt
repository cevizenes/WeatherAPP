package com.example.weather.extensions

import android.view.View
import android.widget.ImageView

/**
 * Created by gokmenbayram on 28.01.2023.
 */


fun View.isVisible(visibility: Boolean) {
    if (visibility) {
        this.visible()
    } else {
        this.gone()
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.asdasd() {

}