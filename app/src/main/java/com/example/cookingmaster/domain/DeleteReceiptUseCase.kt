package com.example.cookingmaster.domain

import com.example.cookingmaster.data.repository.ReceiptRepository
import javax.inject.Inject

interface DeleteReceiptUseCase {
    suspend operator fun invoke(receiptId: Int)
}

class DeleteReceiptUseCaseImpl @Inject constructor(
    private val repository: ReceiptRepository,
): DeleteReceiptUseCase {
    override suspend fun invoke(receiptId: Int) =
        repository.deleteReceipt(receiptId)

}