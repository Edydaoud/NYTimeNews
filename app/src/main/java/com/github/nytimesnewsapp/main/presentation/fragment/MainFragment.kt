package com.github.nytimesnewsapp.main.presentation.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.nytimesnewsapp.R
import com.github.nytimesnewsapp.base.presentation.extension.*
import com.github.nytimesnewsapp.base.presentation.fragment.BaseFragment
import com.github.nytimesnewsapp.base.presentation.viewmodel.BaseViewModel
import com.github.nytimesnewsapp.details.presentation.fragment.DetailsFragment
import com.github.nytimesnewsapp.details.presentation.viewmodel.DetailsViewModel
import com.github.nytimesnewsapp.main.presentation.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet_persistent.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainViewModel.MainViewModelState>(), View.OnClickListener {

    override val layoutId: Int = R.layout.fragment_main
    override val viewModel: MainViewModel by viewModel()

    private val detailsViewModel: DetailsViewModel by sharedViewModel()

    private var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsRecyclerView.layoutManager = uiAdapter.layoutManager(context)
        newsRecyclerView.divider()
        newsRecyclerView.adapter = uiAdapter
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheet.setOnClickListener {
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        oneDayTv.setOnClickListener(this)
        sevenDaysTv.setOnClickListener(this)
        thirtyDaysTv.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetch()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.dating_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dating -> {
                bottomSheetBehavior?.state =
                    if (bottomSheetBehavior?.state == BottomSheetBehavior.STATE_EXPANDED)
                        BottomSheetBehavior.STATE_COLLAPSED
                    else
                        BottomSheetBehavior.STATE_EXPANDED
                true
            }
            else -> false

        }
    }

    override fun onStateChanged(state: BaseViewModel.BaseState<MainViewModel.MainViewModelState>?) {
        state?.let {
            if (state.refreshing && state.list.isEmpty()) {
                progressBar.show()
                newsRecyclerView.hide()
            } else {
                progressBar.hide()
                newsRecyclerView.show()
                uiAdapter.submitList(state.list)
            }
        }
    }

    override fun onViewModelCommand(command: BaseViewModel.BaseCommand) {
        when (command) {
            is MainViewModel.MainViewModelCommand.OnItemClicked -> {
                with(command) {
                    detailsViewModel.setInitialState(
                        id,
                        title,
                        abstract,
                        author,
                        section,
                        date,
                        lastModified,
                        imageUri
                    )

                    activity?.openFragment(
                        newFragment<DetailsFragment>(),
                        replace = false,
                        addToBackStack = true
                    )
                }
            }
        }
    }

    companion object {
        const val ONE_DAY = "1"
        const val SEVEN_DAYS = "7"
        const val THIRTY_DAYS = "30"
    }

    override fun onClick(v: View) {
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED

        when (v.id) {
            R.id.oneDayTv -> viewModel.fetch(ONE_DAY)
            R.id.sevenDaysTv -> viewModel.fetch(SEVEN_DAYS)
            R.id.thirtyDaysTv -> viewModel.fetch(THIRTY_DAYS)
        }
    }
}