package com.example.cookingmaster.presenter.home_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.domain.DeleteReceiptUseCase
import com.example.cookingmaster.domain.GetAllReceiptsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getAllReceiptsUseCase: GetAllReceiptsUseCase,
    private val deleteReceiptUseCase: DeleteReceiptUseCase,
): ViewModel() {
    private val _receipts = MutableLiveData<List<ReceiptModel>>()
    val receipts: LiveData<List<ReceiptModel>>
        get() = _receipts

    fun getAllReceipts() {
        viewModelScope.launch {
            getAllReceiptsUseCase().collect() {
                _receipts.postValue(it)
            }
        }
    }

    fun deleteReceipt(receiptId: Int) {
        viewModelScope.launch {
            deleteReceiptUseCase(receiptId)
        }
    }
}