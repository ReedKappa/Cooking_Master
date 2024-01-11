package com.example.cookingmaster.presenter.receipt_fragment.receipt_textview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cookingmaster.R
import com.example.cookingmaster.databinding.ReceiptTextviewBinding

const val ARG_OBJECT = "object"

class TextViewFragment: Fragment(R.layout.receipt_textview) {
    private val binding: ReceiptTextviewBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            binding.receiptText.text = getString(ARG_OBJECT)
        }
    }
}