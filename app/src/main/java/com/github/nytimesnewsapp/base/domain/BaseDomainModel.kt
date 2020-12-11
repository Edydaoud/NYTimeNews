package com.github.nytimesnewsapp.base.domain

import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel

abstract class BaseDomainModel {
    abstract fun toUIModel(index: Int = 0): BaseUIModel
}