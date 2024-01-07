package com.example.cookingmaster.data.repository

import com.example.cookingmaster.data.db.ReceiptDAO
import com.example.cookingmaster.data.model.ReceiptModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ReceiptRepository {
    suspend fun upsertReceipt(receipt: ReceiptModel)
    suspend fun deleteReceipt(receiptId: Int)
    val getAllReceipts: Flow<List<ReceiptModel>>
}

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptDAO: ReceiptDAO
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

}