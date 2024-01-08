package com.example.cookingmaster.presenter.search_fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.cookingmaster.R
import com.example.cookingmaster.di.ViewModelFactory
import com.example.cookingmaster.di.appComponent
import javax.inject.Inject

class SearchFragment: Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }
}