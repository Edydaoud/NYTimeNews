package com.github.nytimesnewsapp.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.nytimesnewsapp.base.presentation.UiAdapter
import com.github.nytimesnewsapp.base.presentation.extension.observe
import com.github.nytimesnewsapp.base.presentation.viewmodel.BaseViewModel

abstract class BaseFragment<State> : Fragment() {

    abstract val layoutId: Int

    abstract val viewModel: BaseViewModel<State>

    abstract fun onStateChanged(state: BaseViewModel.BaseState<State>?)
    abstract fun onViewModelCommand(command: BaseViewModel.BaseCommand)

    val uiAdapter: UiAdapter by lazy { UiAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutId, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(viewModel.stateLiveData, ::onStateChanged)
        observe(viewModel.commandLiveData, ::onViewModelCommand)
    }

}