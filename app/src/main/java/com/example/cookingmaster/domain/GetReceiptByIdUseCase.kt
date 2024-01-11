package com.example.cookingmaster.domain

import com.example.cookingmaster.data.repository.ReceiptRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetReceiptByIdUseCase {
    suspend operator fun invoke(receiptId: Int): String
}

class GetReceiptByIdUseCaseImpl @Inject constructor(
    private val repository: ReceiptRepository
): GetReceiptByIdUseCase {
    override suspend fun invoke(receiptId: Int): String =
        repository.getReceiptById(receiptId)

}