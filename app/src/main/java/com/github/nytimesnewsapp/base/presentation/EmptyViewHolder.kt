package com.github.nytimesnewsapp.base.presentation

import android.view.View
import com.github.annotations.ViewHolder
import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.model.EmptyUIModel
import com.github.nytimesnewsapp.base.presentation.model.ErrorUIModel


@ViewHolder(EmptyUIModel.LAYOUT, Constants.ViewType.EMPTY_VIEW_TYPE)
class EmptyViewHolder(itemView: View) : BaseViewHolder<EmptyUIModel>(itemView) {
    override fun bindView(position: Int, item: EmptyUIModel) {
    }

    override fun bindViewPayloads(position: Int, item: EmptyUIModel, diffSet: Set<String>) {
    }
}