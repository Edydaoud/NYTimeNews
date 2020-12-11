package com.github.nytimesnewsapp.base.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.nytimesnewsapp.base.Constants
import org.koin.ext.getFullName

data class ErrorUIModel(
    @DrawableRes val errorImage: Int, @StringRes val errorMessage: Int
) : BaseUIModel() {
    override val itemViewType: Int = Constants.ViewType.ERROR_VIEW_TYPE

    override fun getIdentifier(): String {
        return this::class.getFullName()
    }

    override fun areContentsTheSame(other: BaseUIModel): Boolean {
       return other is ErrorUIModel
    }

    companion object {
        const val LAYOUT = "error_item_layout"
    }
}