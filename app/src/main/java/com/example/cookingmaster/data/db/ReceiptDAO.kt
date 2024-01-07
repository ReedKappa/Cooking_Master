package com.example.cookingmaster.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.cookingmaster.data.db.model.ReceiptEntity

@Dao
interface ReceiptDAO {
    @Upsert
    suspend fun upsertReceipt(receipt: ReceiptEntity)

    @Query("DELETE FROM receipts WHERE id=:receiptId")
    suspend fun deleteReceipt(receiptId: Int)
}