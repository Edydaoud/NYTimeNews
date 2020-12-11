package com.github.nytimesnewsapp.details.presentation.model

import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel

data class DetailsUIModel(
    val id: String = "",
    val title: String = "",
    val abstract: String = "",
    val author: String = "",
    val section: String = "",
    val date: String = "",
    val lastModified: String = "",
    val imageUri: String = ""
) : BaseUIModel() {
    override val itemViewType: Int = Constants.ViewType.DETAILS_VIEW_TYPE

    override fun getIdentifier(): String {
        return id
    }

    override fun areContentsTheSame(other: BaseUIModel): Boolean {
        return other is DetailsUIModel
    }

    companion object {
        const val LAYOUT = "details_item_layout"
    }
}