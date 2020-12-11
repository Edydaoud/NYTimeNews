package com.github.nytimesnewsapp.main.presentation.viewmodel

import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel
import com.github.nytimesnewsapp.base.presentation.viewmodel.BaseViewModel
import com.github.nytimesnewsapp.main.domain.NewsUseCase
import com.github.nytimesnewsapp.main.domain.Params
import com.github.nytimesnewsapp.main.presentation.model.HeadLineUIModel

class MainViewModel(
    private val newsUseCase: NewsUseCase
) : BaseViewModel<MainViewModel.MainViewModelState>(MainViewModelState()) {

    data class MainViewModelState(val period: String = "")

    fun fetch(period: String = "1"): Boolean {
        setBaseState { copy(refreshing = true) }
        newsUseCase.fetchData(Params(period)) {
            setBaseState {
                copy(list = it.mapIndexed { index, baseDomainModel ->
                    baseDomainModel.toUIModel(index).apply {
                        onItemClicked = ::onItemClicked
                    }
                }, state = state.copy(period = period), refreshing = false)
            }
        }
        return true
    }

    private fun onItemClicked(item: BaseUIModel) {
        if (item is HeadLineUIModel) {
            with(item) {
                commandLiveData.value = MainViewModelCommand.OnItemClicked(
                    id, title, abstract, author, section, date, lastModified, imageUri
                )
            }
        }
    }

    sealed class MainViewModelCommand : BaseCommand() {
        data class OnItemClicked(
            val id: String = "",
            val title: String = "",
            val abstract: String = "",
            val author: String = "",
            val section: String = "",
            val date: String = "",
            val lastModified: String = "",
            val imageUri: String = ""
        ) : MainViewModelCommand()
    }

}