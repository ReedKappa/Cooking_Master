package com.example.cookingmaster.domain

import com.example.cookingmaster.data.model.ReceiptModel
import com.example.cookingmaster.data.repository.ReceiptRepository
import javax.inject.Inject

interface GetReceiptByNameUseCase {
    suspend operator fun invoke(name: String): Result<List<ReceiptModel>?>
}

class GetReceiptByNameUseCaseImpl @Inject constructor(
    private val repository: ReceiptRepository,
): GetReceiptByNameUseCase {
    override suspend fun invoke(name: String): Result<List<ReceiptModel>?> =
        repository.getReceiptByName(name)

}