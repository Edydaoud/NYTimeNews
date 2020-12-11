package com.github.nytimesnewsapp.base.domain

import com.github.nytimesnewsapp.R
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel
import com.github.nytimesnewsapp.base.presentation.model.ErrorUIModel
import retrofit2.HttpException
import java.net.UnknownHostException

data class ErrorDomainModel(val exception: Exception) : BaseDomainModel() {

    override fun toUIModel(index: Int): BaseUIModel {
        return when (exception) {
            is HttpException, is UnknownHostException -> {
                ErrorUIModel(R.drawable.ic_network_error, R.string.network_error)
            }
            else -> {
                ErrorUIModel(R.drawable.ic_unknown_error, R.string.unknown_error)
            }
        }
    }
}