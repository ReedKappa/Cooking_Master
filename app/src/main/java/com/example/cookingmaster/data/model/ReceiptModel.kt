package com.example.cookingmaster.data.model

import com.example.cookingmaster.data.db.model.ReceiptEntity

data class ReceiptModel(
    val id: Int = 0,
    val name: String,
    val foodType: String,
    val mealTime: String,
    val ingredients: String,
    val receipt: String,
) {
    fun toReceiptEntity(): ReceiptEntity =
        ReceiptEntity(
            id, name, foodType, mealTime, ingredients, receipt
        )
}
