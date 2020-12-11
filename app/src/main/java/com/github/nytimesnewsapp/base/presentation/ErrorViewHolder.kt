package com.github.nytimesnewsapp.base.presentation

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.annotations.ViewHolder
import com.github.nytimesnewsapp.base.Constants
import com.github.nytimesnewsapp.base.presentation.model.ErrorUIModel
import kotlinx.android.synthetic.main.error_item_layout.view.*

@ViewHolder(ErrorUIModel.LAYOUT, Constants.ViewType.ERROR_VIEW_TYPE)
class ErrorViewHolder(itemView: View) : BaseViewHolder<ErrorUIModel>(itemView) {
    override fun bindView(position: Int, item: ErrorUIModel) {
        bindImage(item.errorImage)
        bindErrorMessage(item.errorMessage)
    }

    private fun bindErrorMessage(@StringRes errorMessage: Int) {
        itemView.errorTv.setText(errorMessage)
    }

    private fun bindImage(@DrawableRes errorImage: Int) {
        itemView.errorIv.setImageResource(errorImage)
    }


    override fun bindViewPayloads(position: Int, item: ErrorUIModel, diffSet: Set<String>) {

    }
}