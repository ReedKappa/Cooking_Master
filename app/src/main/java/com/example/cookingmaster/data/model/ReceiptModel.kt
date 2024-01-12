package com.example.cookingmaster.data.model

import com.example.cookingmaster.data.db.model.ReceiptEntity
import com.google.gson.annotations.SerializedName

data class ReceiptModel(
    val id: Int = 0,
    val name: String,
    @SerializedName("tags")
    val foodType: String,
    @SerializedName("categories")
    val mealTime: String,
    @SerializedName("products")
    val ingredients: String,
    val receipt: String,
) {
    fun toReceiptEntity(): ReceiptEntity =
        ReceiptEntity(
            id, name, foodType, mealTime, ingredients, receipt
        )
}
