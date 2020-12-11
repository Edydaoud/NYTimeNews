package com.github.nytimesnewsapp.base.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<in T : BaseUIModel>(
    itemView: View
) : LayoutContainer, RecyclerView.ViewHolder(itemView) {

    override val containerView: View? = itemView

    abstract fun bindView(position: Int, item: T)

    abstract fun bindViewPayloads(position: Int, item: T, diffSet: Set<String>)
}