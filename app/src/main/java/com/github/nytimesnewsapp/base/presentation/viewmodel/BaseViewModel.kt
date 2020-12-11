package com.github.nytimesnewsapp.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.nytimesnewsapp.base.domain.BaseUseCase
import com.github.nytimesnewsapp.base.presentation.model.BaseUIModel
import com.github.nytimesnewsapp.core.SingleLiveEvent

open class BaseViewModel<State>(defaultState: State) : ViewModel() {

    val stateLiveData: MutableLiveData<BaseState<State>> = MutableLiveData<BaseState<State>>()
    val commandLiveData: SingleLiveEvent<BaseCommand> = SingleLiveEvent()

    private val baseState: BaseState<State>?
        get() = stateLiveData.value?.copy()

    internal val state: State?
        get() = baseState?.state

    init {
        stateLiveData.value = BaseState(state = defaultState)
    }


    open fun <ParamsType, ResultType> BaseUseCase<ParamsType, ResultType>.fetchData(
        params: ParamsType,
        onSuccess: suspend (ResultType) -> Unit = {}
    ) {
        invoke(viewModelScope, params, onFinished = onSuccess)
    }

    open class BaseCommand

    inline fun setState(func: State.() -> State) {
        setBaseState { copy(state = state.func()) }
    }

    inline fun setBaseState(func: BaseState<State>.() -> BaseState<State>) {
        stateLiveData.value = stateLiveData.value?.copy()?.func()
    }

    data class BaseState<ExtraState> constructor(
        val state: ExtraState,
        val list: List<BaseUIModel> = emptyList(),
        val refreshing: Boolean = false
    )
}