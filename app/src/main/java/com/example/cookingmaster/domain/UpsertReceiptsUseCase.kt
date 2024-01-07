package com.example.cookingmaster.domain

import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.data.repository.ReceiptRepository
import javax.inject.Inject

interface UpsertReceiptsUseCase {
    suspend operator fun invoke(receipt: ReceiptModel)
}

class UpsertReceiptsUseCaseImpl @Inject constructor(
    private val repository: ReceiptRepository
): UpsertReceiptsUseCase {
    override suspend fun invoke(receipt: ReceiptModel) =
        repository.upsertReceipt(receipt)

}