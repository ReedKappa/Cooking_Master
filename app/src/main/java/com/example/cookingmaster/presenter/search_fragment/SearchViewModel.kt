package com.example.cookingmaster.presenter.search_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingmaster.UiState
import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.domain.DeleteReceiptUseCase
import com.example.cookingmaster.domain.GetReceiptByIdUseCase
import com.example.cookingmaster.domain.GetReceiptByNameUseCase
import com.example.cookingmaster.domain.UpsertReceiptsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val upsertReceiptsUseCase: UpsertReceiptsUseCase,
    private val getReceiptByNameUseCase: GetReceiptByNameUseCase,
) : ViewModel() {
    private val _receiptsList = MutableLiveData<UiState<List<ReceiptModel>>>()
    val receiptList: LiveData<UiState<List<ReceiptModel>>>
        get() = _receiptsList

    fun getReceiptsByName(name: String) {
        viewModelScope.launch {
            val receiptsResult = getReceiptByNameUseCase(name)
            if (receiptsResult.isSuccess) {
                val receipts = receiptsResult.getOrNull()
                receipts ?: _receiptsList.postValue(UiState.Failure("Data was null!"))
                _receiptsList.postValue(receipts?.let { UiState.Success(it) } ?: UiState.Failure("Data was null!"))
            } else _receiptsList.postValue(
                UiState.Failure(receiptsResult.exceptionOrNull()?.message ?: "Very weird problems")
            )
        }
    }

    fun upsertReceipt(receipt: ReceiptModel) {
        viewModelScope.launch {
            upsertReceiptsUseCase(receipt)
        }
    }
}