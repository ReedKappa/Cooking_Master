package com.example.cookingmaster.presenter.receipt_fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.cookingmaster.R
import com.example.cookingmaster.databinding.FragmentReceiptBinding
import com.example.cookingmaster.di.ViewModelFactory
import com.example.cookingmaster.di.appComponent
import com.example.cookingmaster.presenter.home_fragment.HomeAdapter
import com.example.cookingmaster.presenter.home_fragment.HomeViewModel
import com.example.cookingmaster.presenter.receipt_fragment.receipt_textview.ARG_OBJECT
import com.example.cookingmaster.presenter.receipt_fragment.receipt_textview.TextViewFragment
import javax.inject.Inject

class ReceiptFragment: Fragment(R.layout.fragment_receipt) {

    private val args: ReceiptFragmentArgs by navArgs()

    private val binding: FragmentReceiptBinding by viewBinding()


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ReceiptViewModel by viewModels() { viewModelFactory }

    private val receipt = mutableListOf<String>()



    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backToolbar.title = args.receiptName

        viewModel.getReceipt(args.receiptId)

        viewModel.receipt.observe(viewLifecycleOwner) {
            receipt.addAll(it)
            val adapter = ReceiptAdapter(it)
            binding.receiptPager.adapter = adapter
        }

        binding.buttonNext.setOnClickListener {
            binding.receiptPager.setCurrentItem(binding.receiptPager.currentItem + 1, true)
        }

        binding.buttonPrevious.setOnClickListener {
            binding.receiptPager.setCurrentItem(binding.receiptPager.currentItem - 1, true)
        }

        if (args.isFromHome) {
            binding.backToolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            binding.buttonAddReceipt.visibility = View.GONE
        } else {
            binding.backToolbar.setNavigationOnClickListener {
                viewModel.deleteReceipt(args.receiptId)
                findNavController().popBackStack()
            }
            binding.buttonAddReceipt.setOnClickListener {
                findNavController().popBackStack(R.id.homeFragment, false)
            }
        }
    }

    inner class ReceiptAdapter(
        private val receipt: List<String>
    ): FragmentStateAdapter(requireActivity()) {
        override fun getItemCount(): Int = receipt.size

        override fun createFragment(position: Int): Fragment {
            val fragment = TextViewFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_OBJECT, receipt[position])
            }
            return fragment
        }

    }
}