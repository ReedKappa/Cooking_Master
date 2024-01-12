package com.example.cookingmaster.presenter.search_fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cookingmaster.R
import com.example.cookingmaster.UiState
import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.databinding.FragmentSearchBinding
import com.example.cookingmaster.di.ViewModelFactory
import com.example.cookingmaster.di.appComponent
import com.example.cookingmaster.presenter.home_fragment.HomeAdapter
import javax.inject.Inject

class SearchFragment: Fragment(R.layout.fragment_search) {
    private val binding: FragmentSearchBinding by viewBinding()

    private val adapter = SearchAdapter(::onReceiptItemCLick)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToolbar.title = "Поиск блюда"

        addListener()

        with(binding.receiptRecycler) {
            adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.backToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onReceiptItemCLick(receipt: ReceiptModel) {

        viewModel.upsertReceipt(receipt)

        val directions = SearchFragmentDirections.actionSearchFragmentToReceiptFragment(receipt.id, false, receipt.name)
        findNavController().navigate(directions)
    }

    private fun showReceiptList(uiState: UiState<List<ReceiptModel>>) {
        when(uiState) {
            is UiState.Loading -> {
                binding.receiptRecycler.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.receiptRecycler.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }

            is UiState.Failure -> {
                binding.receiptRecycler.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = "Ошибка поиска!"
                binding.receiptRecycler.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }

            is UiState.Success -> {
                binding.receiptRecycler.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.receiptRecycler.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                adapter.submitList(uiState.value)
            }
        }
    }

    private fun addListener() {
        binding.editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString().isNotEmpty()) {

                    viewModel.getReceiptsByName(s.toString().lowercase())

                    viewModel.receiptList.observe(viewLifecycleOwner) {
                        showReceiptList(it)
                    }
                }
            }

        })
    }
}