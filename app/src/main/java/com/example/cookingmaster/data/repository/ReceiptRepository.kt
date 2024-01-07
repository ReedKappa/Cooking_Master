package com.example.cookingmaster.data.repository

import com.example.cookingmaster.data.db.ReceiptDAO
import javax.inject.Inject

interface ReceiptRepository {
}

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptDAO: ReceiptDAO
): ReceiptRepository {

}