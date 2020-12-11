package com.github.nytimesnewsapp.main.presentation.model

import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel

class NewsUIModel(
    id: String = "",
    title: String = "",
    abstract: String = "",
    author: String = "",
    section: String = "",
    date: String = "",
    lastModified: String = "",
    imageUri: String = ""
) : HeadLineUIModel(id, title, abstract, author, section, date, lastModified, imageUri) {

    override val itemViewType: Int = Constants.ViewType.NEWS_VIEW_TYPE

    override fun getIdentifier(): String {
        return id
    }

    override fun areContentsTheSame(other: BaseUIModel): Boolean {
        return other is NewsUIModel && id == other.id
    }

    companion object {
        const val LAYOUT = "news_item_layout"
    }
}