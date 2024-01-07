package com.example.cookingmaster.domain

import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.data.repository.ReceiptRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllReceiptsUseCase {
    operator fun invoke():Flow<List<ReceiptModel>>
}

class GetAllReceiptsUseCaseImpl @Inject constructor(
    private val repository: ReceiptRepository
): GetAllReceiptsUseCase {
    override fun invoke(): Flow<List<ReceiptModel>> =
        repository.getAllReceipts

}