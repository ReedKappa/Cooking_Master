package com.example.cookingmaster.presenter.receipt_fragment

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookingmaster.domain.GetIngredientsByIdUseCase
import com.example.cookingmaster.domain.GetReceiptByIdUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReceiptViewModel @Inject constructor(
    private val getIngredientsByIdUseCase: GetIngredientsByIdUseCase,
    private val getReceiptByIdUseCase: GetReceiptByIdUseCase,
): ViewModel() {

    private val _receipt = MutableLiveData<List<String>>()
    val receipt: LiveData<List<String>>
        get() = _receipt

    fun getReceipt(receiptId: Int, context: Context) {
        viewModelScope.launch {
            val finishedReceipt = mutableListOf<String>()
            val ingredients = getIngredientsByIdUseCase(receiptId)
            //Toast.makeText(context, ingredients, Toast.LENGTH_LONG).show()
            val receipt = getReceiptByIdUseCase(receiptId)
            //Toast.makeText(context, receipt, Toast.LENGTH_LONG).show()

            finishedReceipt.add(ingredients.replace(";", ";\n"))
            finishedReceipt.addAll(receipt.split(";"))
            //Toast.makeText(context, finishedReceipt.toString(), Toast.LENGTH_LONG).show()
            _receipt.postValue(finishedReceipt)
        }
    }
}