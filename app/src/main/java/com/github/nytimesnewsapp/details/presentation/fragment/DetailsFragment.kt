package com.github.nytimesnewsapp.details.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.github.nytimesnewsapp.R
import com.github.nytimesnewsapp.base.presentation.fragment.BaseFragment
import com.github.nytimesnewsapp.base.presentation.viewmodel.BaseViewModel
import com.github.nytimesnewsapp.details.presentation.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DetailsFragment : BaseFragment<DetailsViewModel.DetailsViewModelState>() {
    override val layoutId: Int = R.layout.fragment_details
    override val viewModel: DetailsViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsRecycler.layoutManager = uiAdapter.layoutManager(context)
        detailsRecycler.adapter = uiAdapter
    }

    override fun onStateChanged(state: BaseViewModel.BaseState<DetailsViewModel.DetailsViewModelState>?) {
        state?.let {
            lifecycleScope.launch {
                uiAdapter.submitList(it.list)
            }
        }
    }

    override fun onViewModelCommand(command: BaseViewModel.BaseCommand) {

    }

}