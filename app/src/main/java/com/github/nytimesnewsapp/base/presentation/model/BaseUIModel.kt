package com.github.nytimesnewsapp.base.presentation.model

/** Any view to be rendered must extend this class so the Ui adapter knows how to render */

const val SPAN_COUNT = 6

abstract class BaseUIModel {

    open var spanSize: Int = SPAN_COUNT

    open var onItemClicked: (item: BaseUIModel) -> Unit = {}

    abstract val itemViewType: Int

    abstract fun getIdentifier(): String

    abstract fun areContentsTheSame(other: BaseUIModel): Boolean

    open fun getChangePayload(other: BaseUIModel): Set<String> = emptySet()

}