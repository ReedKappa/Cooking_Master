package com.example.cookingmaster.presenter.receipt_fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.cookingmaster.R
import com.example.cookingmaster.di.ViewModelFactory
import com.example.cookingmaster.di.appComponent
import javax.inject.Inject

class ReceiptFragment: Fragment(R.layout.fragment_receipt) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }
}