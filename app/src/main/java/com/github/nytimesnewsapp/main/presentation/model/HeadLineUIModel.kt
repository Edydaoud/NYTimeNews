package com.github.nytimesnewsapp.main.presentation.model

import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel

open class HeadLineUIModel(
    val id: String = "",
    val title: String = "",
    val abstract: String = "",
    val author: String = "",
    val section: String = "",
    val date: String = "",
    val lastModified: String = "",
    val imageUri: String = ""
) : BaseUIModel() {
    override val itemViewType: Int = Constants.ViewType.HEADLINE_VIEW_TYPE

    override fun getIdentifier(): String {
        return id
    }

    override fun areContentsTheSame(other: BaseUIModel): Boolean {
        return other is HeadLineUIModel && id == other.id
    }

    companion object {
        const val LAYOUT = "headline_item_layout"
    }
}