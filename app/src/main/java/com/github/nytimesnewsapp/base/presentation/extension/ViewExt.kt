package com.github.nytimesnewsapp.base.presentation.extension

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.github.nytimesnewsapp.R


fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .transform(MultiTransformation(CenterCrop(), RoundedCorners(8)))
        .into(this)
}

fun RecyclerView.divider() {
    val dividerItemDecoration = DividerItemDecoration(
        context,
        DividerItemDecoration.VERTICAL
    )
    ContextCompat.getDrawable(context, R.drawable.divider)?.let {
        dividerItemDecoration.setDrawable(it)
    }
    addItemDecoration(dividerItemDecoration)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}