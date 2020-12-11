package com.github.nytimesnewsapp.base.presentation.extension

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.github.nytimesnewsapp.R

fun FragmentActivity.openFragment(
    fragment: Fragment,
    replace: Boolean,
    addToBackStack: Boolean,
    bundle: Bundle? = null
) {
    if (bundle == null)
        lifecycleScope.launchWhenStarted {
            supportFragmentManager.beginTransaction().apply {
                if (replace)
                    replace(R.id.container, fragment, fragment.javaClass.simpleName)
                else
                    add(R.id.container, fragment, fragment.javaClass.simpleName)

                if (addToBackStack)
                    addToBackStack(fragment.javaClass.simpleName)
                commit()
            }
        }
}
