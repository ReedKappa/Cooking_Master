package com.example.cookingmaster.presenter.search_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.databinding.ReceiptItemBinding
import com.example.cookingmaster.presenter.home_fragment.HomeAdapter

class SearchAdapter(
    private val onReceiptItemClick: (ReceiptModel) -> Unit
): ListAdapter<ReceiptModel, SearchAdapter.ReceiptViewHolder>(ReceiptDiffUtil()) {

    class ReceiptViewHolder(
        private val binding: ReceiptItemBinding,
        private val onReceiptItemClick: (ReceiptModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(receipt: ReceiptModel) = with(binding) {
            binding.receiptNameText.text = receipt.name
            binding.receiptTypeText.text = receipt.foodType
            binding.receiptMealTimeText.text = receipt.mealTime
            binding.root.setOnClickListener {
                onReceiptItemClick(receipt)
            }
            binding.deleteButton.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ReceiptItemBinding.inflate(inflater, parent, false)
        return ReceiptViewHolder(binding, onReceiptItemClick)
    }

    override fun onBindViewHolder(holder: ReceiptViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ReceiptDiffUtil : DiffUtil.ItemCallback<ReceiptModel>() {

        override fun areItemsTheSame(oldItem: ReceiptModel, newItem: ReceiptModel): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ReceiptModel, newItem: ReceiptModel): Boolean =
            oldItem == newItem
    }
}