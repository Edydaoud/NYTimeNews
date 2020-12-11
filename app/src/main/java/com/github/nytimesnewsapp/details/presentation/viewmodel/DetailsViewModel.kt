package com.github.nytimesnewsapp.details.presentation.viewmodel

import com.github.nytimesnewsapp.base.presentation.viewmodel.BaseViewModel
import com.github.nytimesnewsapp.details.presentation.model.DetailsUIModel

class DetailsViewModel :
    BaseViewModel<DetailsViewModel.DetailsViewModelState>(DetailsViewModelState()) {

    data class DetailsViewModelState(
        val id: String = "",
        val title: String = "",
        val abstract: String = "",
        val author: String = "",
        val section: String = "",
        val date: String = "",
        val lastModified: String = "",
        val imageUri: String = ""
    ) {
        fun toUiModel(): List<DetailsUIModel> {
            return listOf(
                DetailsUIModel(
                    id, title, abstract, author, section, date, lastModified, imageUri
                )
            )
        }
    }

    fun setInitialState(
        id: String = "",
        title: String = "",
        abstract: String = "",
        author: String = "",
        section: String = "",
        date: String = "",
        lastModified: String = "",
        imageUri: String = ""
    ) {
        val state =
            DetailsViewModelState(
                id,
                title,
                abstract,
                author,
                section,
                date,
                lastModified,
                imageUri
            )
        setBaseState {
            copy(state = state, list = state.toUiModel())
        }
    }

}