package com.github.nytimesnewsapp.main.presentation.viewholder

import android.view.View
import com.github.annotations.ViewHolder
import com.github.nytimesnewsapp.R
import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.BaseViewHolder
import com.github.nytimesnewsapp.base.presentation.extension.hide
import com.github.nytimesnewsapp.base.presentation.extension.load
import com.github.nytimesnewsapp.base.presentation.extension.show
import com.github.nytimesnewsapp.main.presentation.model.HeadLineUIModel
import kotlinx.android.synthetic.main.headline_item_layout.view.*

@ViewHolder("headline_item_layout", Constants.ViewType.HEADLINE_VIEW_TYPE)
class HeadLineViewHolder(itemView: View) : BaseViewHolder<HeadLineUIModel>(itemView) {
    override fun bindView(position: Int, item: HeadLineUIModel) {
        bindTitle(item.title)
        bindDate(item.date)
        bindImage(item.imageUri)
        bindSection(item.section)
    }

    private fun bindSection(section: String) {
        itemView.newsHeadlineSectionTv.text = section
    }

    private fun bindImage(imageUri: String) {
        if (imageUri.isEmpty()) itemView.newsHeadlineIv.hide() else itemView.newsHeadlineIv.show()
        itemView.newsHeadlineIv.load(imageUri)
    }

    private fun bindDate(date: String) {
        itemView.newsHeadlineDateTv.text = date
    }

    private fun bindTitle(title: String) {
        itemView.newsHeadlineTitleTv.text = title
    }

    override fun bindViewPayloads(position: Int, item: HeadLineUIModel, diffSet: Set<String>) {

    }
}