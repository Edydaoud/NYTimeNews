package com.github.nytimesnewsapp.base.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.nytimesnewsapp.R
import com.github.nytimesnewsapp.base.presentation.extension.newFragment
import com.github.nytimesnewsapp.base.presentation.extension.openFragment
import com.github.nytimesnewsapp.main.presentation.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        if (savedInstanceState != null) return
        openFragment(newFragment<MainFragment>(), replace = false, addToBackStack = false)
    }

}