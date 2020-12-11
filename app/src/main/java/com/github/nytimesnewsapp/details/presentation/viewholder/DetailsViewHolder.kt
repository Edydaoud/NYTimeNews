package com.github.nytimesnewsapp.details.presentation.viewholder

import android.view.View
import com.github.annotations.ViewHolder
import com.github.nytimesnewsapp.base.Constants.ViewType.DETAILS_VIEW_TYPE
import com.github.nytimesnewsapp.base.presentation.BaseViewHolder
import com.github.nytimesnewsapp.base.presentation.extension.load
import com.github.nytimesnewsapp.details.presentation.model.DetailsUIModel
import kotlinx.android.synthetic.main.details_item_layout.view.*


@ViewHolder(layout = DetailsUIModel.LAYOUT, viewType = DETAILS_VIEW_TYPE)
class DetailsViewHolder(itemView: View) : BaseViewHolder<DetailsUIModel>(itemView) {
    override fun bindView(position: Int, item: DetailsUIModel) {
        bindTitle(item.title)
        bindDate(item.date)
        bindImage(item.imageUri)
        bindSection(item.section)
        bindAbstract(item.abstract)
        bindAuthor(item.author)
        bindLastModified(item.lastModified)
    }

    private fun bindLastModified(lastModified: String) {

    }

    private fun bindAuthor(author: String) {
        itemView.newsDetailsAuthorTv.text = author
    }

    private fun bindAbstract(abstract: String) {
        itemView.newsDetailsAbstractTv.text = abstract
    }

    private fun bindSection(section: String) {
        itemView.newsDetailsSectionTv.text = section
    }

    private fun bindImage(imageUri: String) {
        itemView.newsDetailsIv.load(imageUri)
    }

    private fun bindDate(date: String) {
        itemView.newsDetailsDateTv.text = date
    }

    private fun bindTitle(title: String) {
        itemView.newsDetailsTitleTv.text = title
    }

    override fun bindViewPayloads(position: Int, item: DetailsUIModel, diffSet: Set<String>) {

    }
}