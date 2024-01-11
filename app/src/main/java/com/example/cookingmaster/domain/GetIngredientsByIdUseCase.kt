package com.example.cookingmaster.domain

import com.example.cookingmaster.data.repository.ReceiptRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetIngredientsByIdUseCase {
    suspend operator fun invoke(receiptId: Int): String
}

class GetIngredientsByIdUseCaseImpl @Inject constructor(
    private val repository: ReceiptRepository,
): GetIngredientsByIdUseCase {
    override suspend fun invoke(receiptId: Int): String =
        repository.getIngredientsById(receiptId)

}