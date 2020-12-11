package com.github.nytimesnewsapp.base.presentation.model

import com.github.nytimesnewsapp.base.Constants
import org.koin.ext.getFullName

class EmptyUIModel : BaseUIModel() {
    override val itemViewType: Int = Constants.ViewType.EMPTY_VIEW_TYPE

    override fun getIdentifier(): String {
        return this::class.getFullName()
    }

    override fun areContentsTheSame(other: BaseUIModel): Boolean {
        return other is EmptyUIModel
    }


    companion object {
        const val LAYOUT = "empty_item_layout"
    }
}