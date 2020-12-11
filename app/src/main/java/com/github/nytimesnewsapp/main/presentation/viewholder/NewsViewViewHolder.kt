package com.github.nytimesnewsapp.main.presentation.viewholder

import android.view.View
import com.github.annotations.ViewHolder
import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.BaseViewHolder
import com.github.nytimesnewsapp.base.presentation.extension.hide
import com.github.nytimesnewsapp.base.presentation.extension.load
import com.github.nytimesnewsapp.base.presentation.extension.show
import com.github.nytimesnewsapp.main.presentation.model.NewsUIModel
import kotlinx.android.synthetic.main.news_item_layout.view.*

@ViewHolder(NewsUIModel.LAYOUT, Constants.ViewType.NEWS_VIEW_TYPE)
class NewsViewViewHolder(itemView: View) : BaseViewHolder<NewsUIModel>(itemView) {
    override fun bindView(position: Int, item: NewsUIModel) {
        bindTitle(item.title)
        bindDate(item.date)
        bindImage(item.imageUri)
        bindSection(item.section)
    }

    private fun bindSection(section: String) {
        itemView.newsSectionTv.text = section
    }

    private fun bindImage(imageUri: String) {
        if (imageUri.isEmpty()) itemView.newsIv.hide() else itemView.newsIv.show()
        itemView.newsIv.load(imageUri)
    }

    private fun bindDate(date: String) {
        itemView.newsDateTv.text = date
    }

    private fun bindTitle(title: String) {
        itemView.newsTitleTv.text = title
    }

    override fun bindViewPayloads(position: Int, item: NewsUIModel, diffSet: Set<String>) {

    }
}