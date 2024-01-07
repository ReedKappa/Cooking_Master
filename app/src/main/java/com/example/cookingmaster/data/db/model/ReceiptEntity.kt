package com.example.cookingmaster.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cookingmaster.data.model.ReceiptModel

@Entity(tableName = "receipts")
data class ReceiptEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val foodType: String,
    val mealTime: String,
    val ingredients: String,
    val receipt: String,
) {
    fun toReceipt(): ReceiptModel =
        ReceiptModel(
            id, name, foodType, mealTime, ingredients, receipt
        )
}