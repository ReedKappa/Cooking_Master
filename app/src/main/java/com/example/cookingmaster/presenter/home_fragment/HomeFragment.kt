package com.example.cookingmaster.presenter.home_fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cookingmaster.R
import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.databinding.FragmentHomeBinding
import com.example.cookingmaster.di.ViewModelFactory
import com.example.cookingmaster.di.appComponent
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter = HomeAdapter(::onReceiptItemClick, ::onDeleteItemClick)

    private val viewModel: HomeViewModel by viewModels() { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.receiptRecycler) {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.receipts.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.getAllReceipts()

        binding.homeToolbar.setOnMenuItemClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(direction)
            true
        }
    }

    private fun onReceiptItemClick(receipt: ReceiptModel) {
        val directions = HomeFragmentDirections.actionHomeFragmentToReceiptFragment(receipt.id, true, receipt.name)
        findNavController().navigate(directions)
    }

    private fun onDeleteItemClick(receiptId: Int) {
        viewModel.deleteReceipt(receiptId)
    }
}