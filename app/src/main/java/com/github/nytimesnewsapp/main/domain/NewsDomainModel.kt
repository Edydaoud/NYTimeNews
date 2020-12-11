package com.github.nytimesnewsapp.main.domain

import com.github.nytimesnewsapp.base.domain.BaseDomainModel
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel
import com.github.nytimesnewsapp.main.presentation.model.HeadLineUIModel
import com.github.nytimesnewsapp.main.presentation.model.NewsUIModel

data class NewsDomainModel(
    val id: String = "",
    val title: String = "",
    val abstract: String = "",
    val author: String = "",
    val section: String = "",
    val date: String = "",
    val lastModified: String = "",
    val imageUri: String = ""
) : BaseDomainModel() {
    override fun toUIModel(index: Int): BaseUIModel {
        return if (index == 0) HeadLineUIModel(
           id, title, abstract, author, section, date, lastModified, imageUri
        ) else NewsUIModel(
            id, title, abstract, author, section, date, lastModified, imageUri
        )
    }
}