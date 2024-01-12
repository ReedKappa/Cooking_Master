package com.example.cookingmaster.data.repository

import com.example.cookingmaster.data.api.ReceiptService
import com.example.cookingmaster.data.db.ReceiptDAO
import com.example.cookingmaster.data.model.ReceiptModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import javax.inject.Inject

interface ReceiptRepository {
    suspend fun upsertReceipt(receipt: ReceiptModel)
    suspend fun deleteReceipt(receiptId: Int)
    val getAllReceipts: Flow<List<ReceiptModel>>
    suspend fun getIngredientsById(receiptId: Int): String
    suspend fun getReceiptById(receiptId: Int): String
    suspend fun getReceiptByName(name: String): Result<List<ReceiptModel>?>
}

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptDAO: ReceiptDAO,
    private val service: ReceiptService,
): ReceiptRepository {
    override suspend fun upsertReceipt(receipt: ReceiptModel) =
        receiptDAO.upsertReceipt(receipt.toReceiptEntity())

    override suspend fun deleteReceipt(receiptId: Int) =
        receiptDAO.deleteReceipt(receiptId)

    override val getAllReceipts: Flow<List<ReceiptModel>>
        get() = receiptDAO.getAllReceipts().map {
            it.map {
                it.toReceipt()
            }
        }

    override suspend fun getIngredientsById(receiptId: Int): String =
        receiptDAO.getIngredientsById(receiptId)

    override suspend fun getReceiptById(receiptId: Int): String =
        receiptDAO.getReceiptsById(receiptId)

    override suspend fun getReceiptByName(name: String): Result<List<ReceiptModel>?> {
        runCatching {
            service.getReceiptByName(name)
        }.fold(
            onSuccess = {
                if (it.isSuccessful)
                    return Result.success(it.body())
                else
                    return Result.failure(HttpException(it))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

}